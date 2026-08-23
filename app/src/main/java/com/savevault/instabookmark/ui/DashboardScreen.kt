package com.savevault.instabookmark.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.savevault.instabookmark.MainViewModel
import com.savevault.instabookmark.data.PostEntity
import android.content.Intent
import android.net.Uri
import com.savevault.instabookmark.ui.components.PostCard
import com.savevault.instabookmark.ui.components.SearchBar
import com.savevault.instabookmark.ui.components.TagFilterChips
import com.savevault.instabookmark.ui.components.TagEditorDialog
import com.savevault.instabookmark.ui.components.AddPostDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val posts = viewModel.filteredPosts.collectAsState(initial = emptyList())
    val allTags = viewModel.allTags.collectAsState(initial = emptySet())
    val selectedTags = viewModel.selectedTags.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()

    val (editingPost, setEditingPost) = remember { mutableStateOf<PostEntity?>(null) }
    val (showAddDialog, setShowAddDialog) = remember { mutableStateOf(false) }
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setShowAddDialog(true) },
                containerColor = Color.White,
                contentColor = Color.Black,
                shape = androidx.compose.foundation.shape.CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Post")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)) {
            
            // Giant minimal header
            Text(
                text = "VAULT.",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Black,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Search bar
            SearchBar(
                query = searchQuery.value,
                onQueryChanged = { viewModel.setSearchQuery(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tag filter chips
            if (allTags.value.isNotEmpty()) {
                TagFilterChips(
                    tags = allTags.value.toList(),
                    selectedTags = selectedTags.value,
                    onTagToggle = { viewModel.toggleTagSelection(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Posts list
            if (posts.value.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No posts found. Tap + to add one or share from Instagram.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(posts.value) { post ->
                        PostCard(
                            post = post, 
                            onTagEdit = { setEditingPost(it) },
                            onDelete = { viewModel.deletePost(it) },
                            onPostClick = { url ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        if (editingPost != null) {
            TagEditorDialog(
                post = editingPost,
                onDismiss = { setEditingPost(null) },
                onSave = { tagsString ->
                    val tags = tagsString.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    viewModel.addTags(editingPost.id, tags)
                    setEditingPost(null)
                }
            )
        }
        if (showAddDialog) {
            AddPostDialog(
                onDismiss = { setShowAddDialog(false) },
                onAdd = { url ->
                    viewModel.savePostFromUrl(url)
                    setShowAddDialog(false)
                }
            )
        }
    }
}

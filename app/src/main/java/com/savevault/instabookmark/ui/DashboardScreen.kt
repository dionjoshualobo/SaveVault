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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.savevault.instabookmark.MainViewModel
import com.savevault.instabookmark.data.PostEntity
import com.savevault.instabookmark.ui.components.PostCard
import com.savevault.instabookmark.ui.components.SearchBar
import com.savevault.instabookmark.ui.components.TagFilterChips
import com.savevault.instabookmark.ui.components.TagEditorDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: MainViewModel) {
    val posts = viewModel.filteredPosts.collectAsState(initial = emptyList())
    val allTags = viewModel.allTags.collectAsState(initial = emptySet())
    val selectedTags = viewModel.selectedTags.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()

    val (editingPost, setEditingPost) = remember { mutableStateOf<PostEntity?>(null) }
    
    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery.value,
                onQueryChanged = { viewModel.setSearchQuery(it) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(8.dp)) {
            // Tag filter chips
            if (allTags.value.isNotEmpty()) {
                TagFilterChips(
                    tags = allTags.value.toList(),
                    selectedTags = selectedTags.value,
                    onTagToggle = { viewModel.toggleTagSelection(it) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            // Posts list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(posts.value) { post ->
                    PostCard(post = post, onTagEdit = { setEditingPost(it) })
                    Spacer(modifier = Modifier.height(8.dp))
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
    }
}

package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.savevault.instabookmark.data.PostEntity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PostCard(
    post: PostEntity,
    onTagEdit: (PostEntity) -> Unit,
    onDelete: (PostEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = post.url, // Using post.url or add imageUrl to PostEntity
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.caption ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    val tagsList = post.tags ?: emptyList()
                    tagsList.forEach { tag ->
                        AssistChip(
                            onClick = { },
                            label = { Text(tag.trim()) }
                        )
                    }
                }
                IconButton(onClick = { onTagEdit(post) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit tags"
                    )
                }
                IconButton(onClick = { onDelete(post) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete post",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

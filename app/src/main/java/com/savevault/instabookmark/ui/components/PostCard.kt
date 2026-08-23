package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
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
    onDelete: (PostEntity) -> Unit,
    onPostClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onPostClick(post.url) },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Black),
        border = androidx.compose.foundation.BorderStroke(1.dp, androidx.compose.ui.graphics.Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (!post.author.isNullOrBlank()) {
                Text(
                    text = "@${post.author}".uppercase(),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            AsyncImage(
                model = post.thumbnailUrl, // Use the correct image url
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(0.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = post.caption ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(color = androidx.compose.ui.graphics.Color.LightGray),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
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
                            label = { Text(tag.trim().uppercase()) },
                            shape = RoundedCornerShape(0.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = androidx.compose.ui.graphics.Color.Black,
                                labelColor = androidx.compose.ui.graphics.Color.White
                            ),
                            border = AssistChipDefaults.assistChipBorder(borderColor = androidx.compose.ui.graphics.Color.White, enabled = true)
                        )
                    }
                }
                IconButton(onClick = { onTagEdit(post) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit tags",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
                IconButton(onClick = { onDelete(post) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete post",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        }
    }
}

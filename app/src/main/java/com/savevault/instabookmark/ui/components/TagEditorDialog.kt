package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.savevault.instabookmark.data.PostEntity

@Composable
fun TagEditorDialog(
    post: PostEntity?,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    if (post == null) return
    val tagsState = remember { mutableStateOf(post.tags.joinToString(", ")) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit Tags") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = tagsState.value,
                    onValueChange = { tagsState.value = it },
                    label = { Text("Comma-separated tags") },
                    placeholder = { Text("e.g., travel, food") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Current tags will be replaced with the entered list.")
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(tagsState.value)
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

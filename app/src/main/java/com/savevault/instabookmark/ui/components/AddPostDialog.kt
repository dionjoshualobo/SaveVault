package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onAdd: (String, List<String>) -> Unit
) {
    val urlState = remember { mutableStateOf("") }
    val tagsState = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Instagram Post") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = urlState.value,
                    onValueChange = { urlState.value = it },
                    label = { Text("Post URL") },
                    placeholder = { Text("https://instagram.com/p/...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = tagsState.value,
                    onValueChange = { tagsState.value = it },
                    label = { Text("Tags (comma separated)") },
                    placeholder = { Text("work,inspiration") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val tags = tagsState.value.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    onAdd(urlState.value, tags)
                    onDismiss()
                }
            ) { Text("Add") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(16.dp)
    )
}

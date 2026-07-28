package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit
) {
    val urlState = remember { MutableState { "" } }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Instagram Post URL") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = urlState.value,
                    onValueChange = { urlState.value = it },
                    label = { Text("Post URL") },
                    placeholder = { Text("https://instagram.com/p/...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAdd(urlState.value)
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

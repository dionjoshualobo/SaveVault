package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text("Search posts…", color = androidx.compose.ui.graphics.Color.DarkGray) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = androidx.compose.ui.graphics.Color.White,
            unfocusedBorderColor = androidx.compose.ui.graphics.Color.DarkGray,
            cursorColor = androidx.compose.ui.graphics.Color.White,
            focusedTextColor = androidx.compose.ui.graphics.Color.White,
            unfocusedTextColor = androidx.compose.ui.graphics.Color.LightGray
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { /* no op */ })
    )
}

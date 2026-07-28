package com.savevault.instabookmark.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TagFilterChips(
    tags: List<String>,
    selectedTags: Set<String>,
    onTagToggle: (String) -> Unit
) {
    Row(modifier = Modifier) {
        tags.forEach { tag ->
            val selected = selectedTags.contains(tag)
            AssistChip(
                onClick = { onTagToggle(tag) },
                label = { androidx.compose.material3.Text(tag) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    labelColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

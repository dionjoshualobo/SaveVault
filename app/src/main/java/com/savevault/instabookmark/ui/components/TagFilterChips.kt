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
                label = { androidx.compose.material3.Text(tag.uppercase(), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (selected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black,
                    labelColor = if (selected) androidx.compose.ui.graphics.Color.Black else androidx.compose.ui.graphics.Color.White
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = androidx.compose.ui.graphics.Color.White,
                    enabled = true
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

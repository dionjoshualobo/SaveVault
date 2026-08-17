package com.savevault.instabookmark.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color.White,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    secondary = androidx.compose.ui.graphics.Color.LightGray,
    background = androidx.compose.ui.graphics.Color.Black,
    surface = androidx.compose.ui.graphics.Color(0xFF111111),
    onSurface = androidx.compose.ui.graphics.Color.White
)

@Composable
fun SaveVaultTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}

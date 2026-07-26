package com.savevault.instabookmark

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.savevault.instabookmark.ui.DashboardScreen
import com.savevault.instabookmark.ui.theme.SaveVaultTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIncomingShare(intent)
        setContent {
            SaveVaultTheme {
                DashboardScreen(viewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIncomingShare(it) }
    }

    private fun handleIncomingShare(intent: Intent) {
        if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            sharedText?.let { url ->
                // Assume the URL is an Instagram post URL, initiate saving
                viewModel.savePostFromUrl(url)
            }
        }
    }
}

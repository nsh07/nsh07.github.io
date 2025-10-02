package org.nsh07.nsh07

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.nsh07.nsh07.ui.AppScreen
import org.nsh07.nsh07.ui.theme.Nsh07Theme

@Composable
fun App() {
    Nsh07Theme {
        Surface {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(colorScheme.surface).fillMaxSize()
            ) {
                AppScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
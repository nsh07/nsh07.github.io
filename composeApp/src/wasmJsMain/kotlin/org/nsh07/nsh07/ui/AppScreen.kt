package org.nsh07.nsh07.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            "Nishant Mishra",
            style = typography.displayLarge,
            modifier = Modifier.padding(32.dp)
        )
    }
}

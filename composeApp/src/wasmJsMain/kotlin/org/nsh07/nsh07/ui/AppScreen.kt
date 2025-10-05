package org.nsh07.nsh07.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.nsh07.nsh07.ui.homeScreen.AppHomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(
    modifier: Modifier = Modifier
) {
    AppHomeScreen(modifier)
}

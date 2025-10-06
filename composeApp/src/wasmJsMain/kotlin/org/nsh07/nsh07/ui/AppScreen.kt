package org.nsh07.nsh07.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.nsh07.nsh07.ui.homeScreen.AppHomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(
    viewModel: UiViewModel = viewModel(factory = UiViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val projectsState by viewModel.projectsState.collectAsState()

    AppHomeScreen(projectsState, modifier)
}

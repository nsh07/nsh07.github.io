package org.nsh07.nsh07.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.nsh07.nsh07.network.GitHubApiClient
import org.nsh07.nsh07.network.createHttpClient
import org.nsh07.nsh07.ui.homeScreen.AppHomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(
    viewModel: UiViewModel = remember { UiViewModel(GitHubApiClient(createHttpClient())) },
    modifier: Modifier = Modifier
) {
    val projectsState by viewModel.projectsState.collectAsState()

    AppHomeScreen(projectsState, modifier)
}

package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.runtime.Immutable
import org.nsh07.nsh07.network.Repo

@Immutable
data class ProjectsState(
    val isLoading: Boolean = false,
    val projects: List<Repo> = emptyList(),
    val error: String? = null
)

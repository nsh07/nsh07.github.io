package org.nsh07.nsh07.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.nsh07.nsh07.network.GitHubApiClient
import org.nsh07.nsh07.network.createHttpClient
import org.nsh07.nsh07.ui.homeScreen.ProjectsState
import org.nsh07.nsh07.util.onError
import org.nsh07.nsh07.util.onSuccess

class UiViewModel(
    private val githubClient: GitHubApiClient
) : ViewModel() {
    private val _projectsState = MutableStateFlow(ProjectsState())
    val projectsState: StateFlow<ProjectsState> = _projectsState.asStateFlow()

    init {
        loadProjects()
    }

    fun loadProjects() {
        viewModelScope.launch {
            githubClient.getTopRepos("nsh07")
                .onSuccess {
                    _projectsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = null,
                            projects = it.repos
                        )
                    }
                }
                .onError {
                    _projectsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = it.name,
                            projects = emptyList()
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val client = GitHubApiClient(createHttpClient())
                UiViewModel(client)
            }
        }
    }

}
package org.nsh07.nsh07.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRepos(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    @SerialName("items")
    val repos: List<Repo>,
    @SerialName("total_count")
    val totalCount: Int
)
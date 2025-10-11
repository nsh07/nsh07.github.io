package org.nsh07.nsh07.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    @SerialName("description")
    val description: String,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("language")
    val language: String,
    @SerialName("license")
    val license: License?,
    @SerialName("name")
    val name: String,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("topics")
    val topics: List<String>,
)
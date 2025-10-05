package org.nsh07.nsh07.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(DefaultRequest) {
            header("X-GitHub-Api-Version", "2022-11-28")
            header(HttpHeaders.UserAgent, "nsh07.github.io/1.0.0 (nishant.28@outlook.com) ktor/3.3.0")
        }
    }
}
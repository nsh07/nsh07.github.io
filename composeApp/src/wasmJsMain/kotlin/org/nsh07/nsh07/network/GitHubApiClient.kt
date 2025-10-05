package org.nsh07.nsh07.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.network.*
import kotlinx.serialization.SerializationException
import org.nsh07.nsh07.util.NetworkError
import org.nsh07.nsh07.util.Result

class GitHubApiClient(
    private val client: HttpClient
) {

    private val url = "https://api.github.com/search/repositories"

    suspend fun getTopRepos(username: String): Result<TopRepos, NetworkError> {
        val response = try {
            client.get(url) {
                header(HttpHeaders.Accept, ContentType.Application.Json.toString())

                parameter("q", "user:$username")
                parameter("sort", "stars")
                parameter("order", "desc")
                parameter("per_page", "3")
            }
        } catch (_: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (_: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val topRepos = response.body<TopRepos>()
                Result.Success(topRepos)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}
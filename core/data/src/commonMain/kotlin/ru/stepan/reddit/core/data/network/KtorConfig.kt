package ru.stepan.reddit.core.data.network

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal expect class HttpEngineFactory() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}

internal object KtorConfiguration {
    const val CONNECTION_TIMEOUT = 15000L
    const val REQUEST_TIMEOUT = 30000L
}

internal val KtorJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.commonConfig() {
    install(ContentNegotiation) {
        json(KtorJson)
    }
    HttpResponseValidator {
        validateResponse { response ->
            val statusCode = response.status
            if (statusCode.value in 400..499) {
                throw ClientRequestException(response, "Client error ${statusCode.value}")
            }
            if (statusCode.value >= 500) {
                throw ServerResponseException(response, "Server error ${statusCode.value}")
            }
        }

        handleResponseExceptionWithRequest { exception, _ ->
            Napier.e(tag = "HTTP CLIENT", message = "HTTP Exception: $exception")
        }
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
    }

    install(HttpTimeout) {
        connectTimeoutMillis = KtorConfiguration.CONNECTION_TIMEOUT
        requestTimeoutMillis = KtorConfiguration.REQUEST_TIMEOUT
    }
}
package ru.stepan.reddit.core.data.network

import dev.lokksmith.client.Client
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.client.request.parameter

internal fun authorizedKtorClient(clientFactory: StepikOAuthClientFactory): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()

        install(Auth) {
            bearer {
                loadTokens {
                    val client = clientFactory.getOrCreate()
                    val tokens = client.tokens.value ?: return@loadTokens null
                    tokens.toBearer()
                }

                refreshTokens {
                    runCatching {
                        val client = clientFactory.getOrCreate()
                        client.refresh().toBearer()
                    }.getOrNull()
                }
            }
        }
    }
}

private fun Client.Tokens.toBearer(): BearerTokens {
    return BearerTokens(accessToken = accessToken.token, refreshToken = refreshToken?.token)
}
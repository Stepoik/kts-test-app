package ru.stepan.reddit.core.data.network

import dev.lokksmith.SingletonLokksmithProvider.lokksmith
import dev.lokksmith.client.Client
import dev.lokksmith.id
import dev.lokksmith.metadata

class StepikOAuthClientFactory {
    suspend fun getOrCreate(): Client {
        return lokksmith.getOrCreate(
            key = STEPIK_CLIENT_ID,
            options = Client.Options(leewaySeconds = 30)
        ) {
            id = STEPIK_CLIENT_ID
            metadata = Client.Metadata(
                issuer = "",
                authorizationEndpoint = STEPIK_AUTHORIZATION_ENDPOINT,
                tokenEndpoint = STEPIK_TOKEN_ENDPOINT
            )
        }
    }

    companion object {
        private const val STEPIK_CLIENT_ID = "AB3JTcapjiIHD3CRaLeeWINCoib4iBkArkZt2M86"
        private const val STEPIK_AUTHORIZATION_ENDPOINT = "https://stepik.org/oauth2/authorize"
        private const val STEPIK_TOKEN_ENDPOINT = "http://95.31.139.53:41032/token"

        const val REDIRECT_URL = "http://95.31.139.53:41032/oauth/callback"
    }
}
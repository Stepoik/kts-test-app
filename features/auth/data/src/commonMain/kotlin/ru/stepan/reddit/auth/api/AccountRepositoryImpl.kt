package ru.stepan.reddit.auth.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.lokksmith.client.request.flow.AuthFlow
import dev.lokksmith.client.request.flow.authorizationCode.AuthorizationCodeFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.stepan.reddit.auth.api.models.Account
import ru.stepan.reddit.core.data.network.StepikOAuthClientFactory

class AccountRepositoryImpl(
    private val datastore: DataStore<Preferences>,
    private val httpClient: HttpClient,
    private val stepikOAuthClientFactory: StepikOAuthClientFactory
) : AccountRepository {
    override val activeAccount: Flow<Account?> = datastore.data.map {
        stepikOAuthClientFactory.getOrCreate().tokens.value ?: return@map null
        Account(username = "", "")
    }

    override suspend fun updateAccount(): Result<Unit> {
        return runCatching {
            httpClient.get("")
        }
    }

    override suspend fun clearAccount(): Result<Unit> {
        return runCatching {
            stepikOAuthClientFactory.getOrCreate().resetTokens()
        }
    }

    override suspend fun getAuthInitiation(): AuthFlow.Initiation {
        val request = AuthorizationCodeFlow.Request(
            redirectUri = StepikOAuthClientFactory.REDIRECT_URL
        )
        return stepikOAuthClientFactory.getOrCreate()
            .authorizationCodeFlow(request = request)
            .prepare()
    }
}
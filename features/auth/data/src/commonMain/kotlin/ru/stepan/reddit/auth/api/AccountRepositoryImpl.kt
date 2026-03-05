package ru.stepan.reddit.auth.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.stepan.reddit.auth.api.models.Account
import ru.stepan.reddit.auth.network.AccountKtorDatastore
import ru.stepan.reddit.core.data.network.StepikOAuthClientFactory

class AccountRepositoryImpl(
    private val datastore: DataStore<Preferences>,
    private val accountDatastore: AccountKtorDatastore,
    private val stepikOAuthClientFactory: StepikOAuthClientFactory
) : AccountRepository {
    override val activeAccount: Flow<Account?> = datastore.data.map {
        stepikOAuthClientFactory.getOrCreate().tokens.value ?: return@map null
        Account(username = "", "")
    }

    override suspend fun updateAccount(): Result<Unit> {
        return runCatching {
            val account = accountDatastore.getAccount().getOrThrow()
            if (account.isGuest) {
                throw IllegalStateException("Account is guest")
            }
            println(account)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
            stepikOAuthClientFactory.getOrCreate().resetTokens()
        }
    }
}
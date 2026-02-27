package ru.stepan.reddit.auth.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.stepan.reddit.auth.api.errors.IncorrectCredentialsError
import ru.stepan.reddit.auth.api.models.Account
import ru.stepan.reddit.auth.api.models.AuthCredentials

class AccountRepositoryImpl(
    private val datastore: DataStore<Preferences>
) : AccountRepository {
    override val activeAccount: Flow<Account?> = datastore.data.map {
        val username = it[ACCOUNT_USERNAME_KEY] ?: return@map null
        Account(username = username, "")
    }

    override suspend fun login(credentials: AuthCredentials): Result<Unit> {
        return runCatching {
            if (!credentials.isCorrect()) {
                datastore.edit {
                    it[ACCOUNT_USERNAME_KEY] = "stepa2004"
                }
            } else {
                throw IncorrectCredentialsError()
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
            datastore.edit {
                it.remove(ACCOUNT_USERNAME_KEY)
            }
        }
    }

    override suspend fun getMe(): Result<Account> {
        return runCatching {
            activeAccount.first()!!
        }
    }

    private fun AuthCredentials.isCorrect(): Boolean {
        return username == "holop300@gmail.com" && password == "stepa2004"
    }

    companion object {
        private val ACCOUNT_USERNAME_KEY = stringPreferencesKey("account_username")
    }
}
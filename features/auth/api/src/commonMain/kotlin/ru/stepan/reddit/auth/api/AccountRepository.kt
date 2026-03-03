package ru.stepan.reddit.auth.api

import kotlinx.coroutines.flow.Flow
import ru.stepan.reddit.auth.api.models.Account
import ru.stepan.reddit.auth.api.models.AuthCredentials

interface AccountRepository {
    val activeAccount: Flow<Account?>

    suspend fun login(credentials: AuthCredentials): Result<Unit>

    suspend fun logout(): Result<Unit>

    suspend fun getMe(): Result<Account>
}
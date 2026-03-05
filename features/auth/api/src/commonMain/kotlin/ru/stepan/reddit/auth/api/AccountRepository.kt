package ru.stepan.reddit.auth.api

import dev.lokksmith.client.request.flow.AuthFlow
import kotlinx.coroutines.flow.Flow
import ru.stepan.reddit.auth.api.models.Account

interface AccountRepository {
    val activeAccount: Flow<Account?>

    suspend fun updateAccount(): Result<Unit>

    suspend fun clearAccount(): Result<Unit>

    suspend fun getAuthInitiation(): AuthFlow.Initiation
}
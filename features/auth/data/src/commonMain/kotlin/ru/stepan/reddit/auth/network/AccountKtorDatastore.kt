package ru.stepan.reddit.auth.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.stepan.reddit.auth.network.models.GetStepicsDto
import ru.stepan.reddit.auth.network.models.UserDto

class AccountKtorDatastore(
    private val httpClient: HttpClient
) {
    suspend fun getAccount(): Result<UserDto> {
        return runCatching {
            httpClient.get("stepics/1").body<GetStepicsDto>().users.first()
        }
    }
}
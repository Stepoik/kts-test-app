package ru.stepan.reddit.courses.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.stepan.reddit.core.common.result.coroutinesRunCatching
import ru.stepan.reddit.core.data.network.listParameters
import ru.stepan.reddit.courses.api.dtos.GetUsersResponse
import ru.stepan.reddit.courses.api.mappers.toDomain
import ru.stepan.reddit.courses.api.models.User

class UserRepositoryImpl(
    private val httpClient: HttpClient
) : UserRepository {
    override suspend fun getAuthors(ids: List<Long>): Result<List<User>> {
        return coroutinesRunCatching {
            httpClient.get("users") {
                listParameters("ids[]", ids)
            }.body<GetUsersResponse>()
                .users
                .map { it.toDomain() }
        }
    }
}
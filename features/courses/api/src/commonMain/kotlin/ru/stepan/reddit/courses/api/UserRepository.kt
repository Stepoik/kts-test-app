package ru.stepan.reddit.courses.api

import ru.stepan.reddit.courses.api.models.User

interface UserRepository {
    suspend fun getAuthors(ids: List<Long>): Result<List<User>>
}
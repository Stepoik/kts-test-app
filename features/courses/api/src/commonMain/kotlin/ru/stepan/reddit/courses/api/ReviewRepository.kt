package ru.stepan.reddit.courses.api

import ru.stepan.reddit.courses.api.models.Review

interface ReviewRepository {
    suspend fun getReviews(ids: List<Long>): Result<List<Review>>
}
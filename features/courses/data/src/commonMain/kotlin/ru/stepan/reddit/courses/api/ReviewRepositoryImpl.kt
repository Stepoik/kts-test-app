package ru.stepan.reddit.courses.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.stepan.reddit.core.common.result.coroutinesRunCatching
import ru.stepan.reddit.core.data.network.listParameters
import ru.stepan.reddit.courses.api.dtos.GetCourseReviewResponse
import ru.stepan.reddit.courses.api.mappers.toDomain
import ru.stepan.reddit.courses.api.models.Review

class ReviewRepositoryImpl(
    private val httpClient: HttpClient
) : ReviewRepository {
    override suspend fun getReviews(ids: List<Long>): Result<List<Review>> {
        return coroutinesRunCatching {
            httpClient.get("course-review-summaries") {
                listParameters("ids[]", ids)
            }.body<GetCourseReviewResponse>()
                .courseReviewSummaries
                .map { it.toDomain() }
        }
    }
}
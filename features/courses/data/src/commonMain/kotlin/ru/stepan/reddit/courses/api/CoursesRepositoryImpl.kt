package ru.stepan.reddit.courses.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import ru.stepan.reddit.core.common.result.coroutinesRunCatching
import ru.stepan.reddit.core.data.network.listParameters
import ru.stepan.reddit.core.data.network.models.nextPage
import ru.stepan.reddit.courses.api.dtos.GetCoursesResponse
import ru.stepan.reddit.courses.api.dtos.GetRecommendationsResponse
import ru.stepan.reddit.courses.api.dtos.SearchResultsResponse
import ru.stepan.reddit.courses.api.mappers.toDomain
import ru.stepan.reddit.courses.api.models.Course
import ru.stepan.reddit.courses.api.models.CoursePage

class CoursesRepositoryImpl(
    private val httpClient: HttpClient
) : CoursesRepository {
    override suspend fun getRecommendations(page: Int): Result<CoursePage> =
        withContext(Dispatchers.IO) {
            coroutinesRunCatching {
                val body =
                    httpClient.get("course-recommendations").body<GetRecommendationsResponse>()
                val courses = getCoursesByIds(body.courses.flatMap { it.courses })
                CoursePage(nextPage = body.meta.nextPage, courses = courses)
            }
        }

    override suspend fun findCourses(
        query: String,
        page: Int
    ): Result<CoursePage> = withContext(Dispatchers.IO) {
        coroutinesRunCatching {
            val body = httpClient.get("search-results") {
                parameter(
                    "order",
                    "conversion_rate__none%2Crating__none%2Cquality__none%2Cpaid_weight__none%2Csearch_boost__none"
                )
                parameter("query", query)
                parameter("page", page)
                parameter("type", "course")
            }.body<SearchResultsResponse>()

            val courses = getCoursesByIds(body.searchResults.map { it.id })
            CoursePage(
                nextPage = body.meta.nextPage,
                courses = courses
            )
        }
    }

    override suspend fun getCourseById(id: Long): Result<Course> {
        return Result.failure(IllegalStateException())
    }

    private suspend fun getCoursesByIds(ids: List<Long>): List<Course> {
        val body = httpClient.get("courses") {
            listParameters("ids[]", ids)
        }.body<GetCoursesResponse>()
        return body.courses.map { it.toDomain() }

    }
}
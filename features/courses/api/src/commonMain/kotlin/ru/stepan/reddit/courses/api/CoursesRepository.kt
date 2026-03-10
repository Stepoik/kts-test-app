package ru.stepan.reddit.courses.api

import ru.stepan.reddit.courses.api.models.Course
import ru.stepan.reddit.courses.api.models.CoursePage

interface CoursesRepository {
    suspend fun getRecommendations(page: Int): Result<CoursePage>

    suspend fun findCourses(query: String, page: Int): Result<CoursePage>

    suspend fun getCourseById(id: Long): Result<Course>
}
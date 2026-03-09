package ru.stepan.reddit.cources.list

import ru.stepan.reddit.courses.api.models.CoursePage

fun interface CoursesLoader {
    suspend fun getCourses(page: Int): Result<CoursePage>
}
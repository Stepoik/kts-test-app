package ru.stepan.reddit.courses.api.models

data class CoursePage(
    val courses: List<Course>,
    val nextPage: Int?
)

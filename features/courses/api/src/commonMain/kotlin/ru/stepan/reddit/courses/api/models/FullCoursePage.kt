package ru.stepan.reddit.courses.api.models

data class FullCoursePage(
    val courses: List<FullCourse>,
    val nextPage: Int?
)

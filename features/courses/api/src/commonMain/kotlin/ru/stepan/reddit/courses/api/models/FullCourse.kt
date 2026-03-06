package ru.stepan.reddit.courses.api.models

data class FullCourse(
    val course: Course,
    val review: Review,
    val author: List<User>
)

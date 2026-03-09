package ru.stepan.reddit.course.details.main

import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailsState(
    val isLoading: Boolean = false,
    val course: FullCourseVO? = null
)

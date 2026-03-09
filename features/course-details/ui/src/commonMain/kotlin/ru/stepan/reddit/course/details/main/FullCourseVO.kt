package ru.stepan.reddit.course.details.main

import kotlinx.serialization.Serializable

@Serializable
data class FullCourseVO(
    val title: String,
    val summary: String,
    val description: String,
    val acquiredSkills: List<String>
)

package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CourseReviewSummariesDto(
    val id: Long,
    val course: Long,
    val average: Double,
    val count: Int
)

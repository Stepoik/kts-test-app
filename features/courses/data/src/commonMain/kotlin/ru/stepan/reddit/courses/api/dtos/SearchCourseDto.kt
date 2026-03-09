package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCourseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("course_title")
    val title: String,
    @SerialName("course_cover")
    val cover: String,
    @SerialName("score")
    val score: Double,
    @SerialName("course_owner")
    val owner: Long,
    @SerialName("course")
    val course: Long
)

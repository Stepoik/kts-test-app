package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.data.network.models.PageMeta

@Serializable
data class GetCoursesResponse(
    val meta: PageMeta,
    val courses: List<CourseDto>
)

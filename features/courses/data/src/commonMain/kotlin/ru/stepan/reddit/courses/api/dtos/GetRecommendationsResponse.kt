package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.data.network.models.PageMeta

@Serializable
data class GetRecommendationsResponse(
    val meta: PageMeta,
    @SerialName("course-recommendations")
    val courses: List<CourseRecommendationsDto>
) {
    @Serializable
    data class CourseRecommendationsDto(
        val courses: List<Long>
    )
}

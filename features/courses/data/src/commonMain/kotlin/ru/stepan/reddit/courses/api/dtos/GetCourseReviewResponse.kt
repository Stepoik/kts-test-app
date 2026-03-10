package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.data.network.models.PageMeta

@Serializable
data class GetCourseReviewResponse(
    val meta: PageMeta,
    @SerialName("course-review-summaries")
    val courseReviewSummaries: List<CourseReviewSummariesDto>
)

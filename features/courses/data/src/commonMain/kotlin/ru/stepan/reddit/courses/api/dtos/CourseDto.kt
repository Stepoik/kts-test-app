package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("summary")
    val summary: String,
    @SerialName("cover")
    val cover: String,
    @SerialName("owner")
    val owner: Long,
    @SerialName("title")
    val title: String,
    @SerialName("review_summary")
    val reviewId: Long,
    @SerialName("time_to_complete")
    val timeToComplete: Long,
    @SerialName("display_price")
    val displayPrice: String,
    @SerialName("default_promo_code_price")
    val displayPromoPrice: String? = null,
    @SerialName("authors")
    val authors: List<Long>,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    @SerialName("learners_count")
    val learnersCount: Int,
    @SerialName("with_certificate")
    val withCertificate: Boolean
)

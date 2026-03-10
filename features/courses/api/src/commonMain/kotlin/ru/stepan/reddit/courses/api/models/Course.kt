package ru.stepan.reddit.courses.api.models

data class Course(
    val id: Long,
    val title: String,
    val summary: String,
    val description: String,
    val acquiredSkills: List<String>,
    val cover: String,
    val owner: Long,
    val authors: List<Long>,
    val reviewId: Long,
    val isInWishlist: Boolean,
    val displayPrice: String,
    val displayDiscountPrice: String?,
    val learnersCount: Int,
    val timeToCompleteSeconds: Long?,
    val withCertificate: Boolean
)

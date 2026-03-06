package ru.stepan.reddit.courses.api.models

data class Course(
    val id: Long,
    val title: String,
    val cover: String,
    val owner: Long,
    val authors: List<Long>,
    val reviewId: Long,
    val isFavorite: Boolean,
    val displayPrice: String,
    val displayDiscountPrice: String?,
    val learnersCount: Int,
    val timeToComplete: Long,
    val withCertificate: Boolean
)

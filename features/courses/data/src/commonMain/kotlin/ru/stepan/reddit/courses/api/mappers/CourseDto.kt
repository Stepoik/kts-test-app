package ru.stepan.reddit.courses.api.mappers

import ru.stepan.reddit.courses.api.dtos.CourseDto
import ru.stepan.reddit.courses.api.models.Course

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        cover = cover,
        owner = owner,
        authors = authors,
        reviewId = reviewId,
        isFavorite = isFavorite,
        displayPrice = displayPrice,
        displayDiscountPrice = displayPromoPrice,
        learnersCount = learnersCount,
        timeToCompleteSeconds = timeToComplete,
        withCertificate = withCertificate
    )
}
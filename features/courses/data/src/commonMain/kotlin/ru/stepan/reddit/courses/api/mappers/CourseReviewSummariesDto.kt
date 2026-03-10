package ru.stepan.reddit.courses.api.mappers

import ru.stepan.reddit.courses.api.dtos.CourseReviewSummariesDto
import ru.stepan.reddit.courses.api.models.Review

fun CourseReviewSummariesDto.toDomain(): Review {
    return Review(
        id = id,
        average = average,
        count = count
    )
}
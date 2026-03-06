package ru.stepan.reddit.cources.list

import ru.stepan.reddit.cources.composables.CourseReview
import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.courses.api.models.FullCourse

fun FullCourse.toVO(): CourseVO {
    return CourseVO(
        id = course.id,
        title = course.title,
        cover = course.cover,
        authors = author.map { it.fullName }.joinToString(", "),
        isLiked = course.isFavorite,
        displayDiscountPrice = course.displayDiscountPrice,
        displayPrice = course.displayPrice,
        review = getReview()
    )
}

fun FullCourse.getReview(): CourseReview {
    return CourseReview(
        average = review.average.toString(),
        votesCount = review.count.toString(),
        learnersCount = course.learnersCount.toString(),
        starsCount = review.average,
        timeToComplete = course.timeToComplete.toString(),
        withCertificate = course.withCertificate
    )
}
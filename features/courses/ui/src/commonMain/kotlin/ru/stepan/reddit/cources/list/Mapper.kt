package ru.stepan.reddit.cources.list

import ru.stepan.reddit.cources.composables.CourseReview
import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.courses.api.models.FullCourse
import kotlin.math.pow
import kotlin.math.roundToInt

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
        average = review.average.round(2).toString(),
        votesCount = review.count.formatThousands(),
        learnersCount = course.learnersCount.formatThousands(),
        starsCount = review.average,
        timeToComplete = course.timeToCompleteSeconds.secondsToHours(),
        withCertificate = course.withCertificate
    )
}

private fun Double.round(decimals: Int): Double {
    val offset = 10.0.pow(decimals)
    return (this * offset).roundToInt() / offset
}

private fun Int.formatThousands(): String {
    return if (this >= 1000) {
        val thousands = this / 1000
        val hundreds = (this % 1000) / 10
        if (hundreds != 0) {
            "$thousands.${hundreds}К"
        } else {
            "${thousands}К"
        }
    } else toString()
}

private fun Long.secondsToHours(): Int {
    return (this / 3600).toInt()
}
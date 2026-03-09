package ru.stepan.reddit.courses.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ru.stepan.reddit.courses.api.ReviewRepository
import ru.stepan.reddit.courses.api.UserRepository
import ru.stepan.reddit.courses.api.models.Course
import ru.stepan.reddit.courses.api.models.CoursePage
import ru.stepan.reddit.courses.api.models.FullCourse
import ru.stepan.reddit.courses.api.models.FullCoursePage
import ru.stepan.reddit.courses.api.models.Review
import ru.stepan.reddit.courses.api.models.User

typealias CourseLoader = suspend () -> Result<CoursePage>


class GetFullCoursesUseCase(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(
        courseLoader: CourseLoader
    ): Result<FullCoursePage> = withContext(Dispatchers.Default) {
        val coursesPage = courseLoader.invoke().getOrElse { return@withContext Result.failure(it) }
        val fullCourses = getFullCourses(coursesPage.courses)
        fullCourses.map {
            FullCoursePage(
                courses = it,
                nextPage = coursesPage.nextPage
            )
        }
    }

    private suspend fun getFullCourses(courses: List<Course>): Result<List<FullCourse>> {
        val reviewsIds = courses.map { it.reviewId }
        val authorsIds = courses.flatMap { it.authors }
        return coroutineScope {
            val reviewsJob = async {
                reviewRepository.getReviews(reviewsIds)
            }

            val authorsJob = async {
                userRepository.getAuthors(authorsIds)
            }
            val reviews = reviewsJob.await().getOrElse { return@coroutineScope Result.failure(it) }
            val authors = authorsJob.await().getOrElse { return@coroutineScope Result.failure(it) }
            combineCourses(courses, reviews, authors)
        }
    }

    private fun combineCourses(
        courses: List<Course>,
        reviews: List<Review>,
        authors: List<User>
    ): Result<List<FullCourse>> {
        return runCatching {
            val authorsMap = authors.associateBy { it.id }
            val reviewsMap = reviews.associateBy { it.id }
            courses.map {
                FullCourse(
                    course = it,
                    review = reviewsMap[it.reviewId]!!,
                    author = it.authors.map { authorsMap[it]!! }
                )
            }
        }
    }
}
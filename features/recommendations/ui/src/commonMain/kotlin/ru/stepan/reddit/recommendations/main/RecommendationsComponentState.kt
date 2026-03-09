package ru.stepan.reddit.recommendations.main

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.cources.list.CourseListError

@Serializable
data class RecommendationsComponentState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    @Transient
    val courses: List<CourseVO> = listOf(),
    val error: CourseListError? = null,
) {
    fun asScreenState(): RecommendationsScreenState {
        return when {
            isLoading && courses.isEmpty() -> RecommendationsScreenState.Loading(isRefreshing)
            error != null && courses.isEmpty() -> RecommendationsScreenState.Error(
                isRefreshing = isRefreshing,
                error = error
            )

            else -> RecommendationsScreenState.Loaded(
                isRefreshing = isRefreshing,
                courses = courses,
                isLoading = isLoading
            )
        }
    }
}

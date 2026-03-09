package ru.stepan.reddit.recommendations.main

import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.cources.list.CourseListError

sealed interface RecommendationsScreenState {
    val isRefreshing: Boolean

    data class Loading(
        override val isRefreshing: Boolean
    ) : RecommendationsScreenState

    data class Loaded(
        override val isRefreshing: Boolean,
        val courses: List<CourseVO>,
        val isLoading: Boolean
    ) : RecommendationsScreenState

    data class Error(
        override val isRefreshing: Boolean,
        val error: CourseListError
    ) : RecommendationsScreenState
}
package ru.stepan.reddit.recommendations.main

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.cources.list.CourseListError

@Serializable
data class RecommendationsState(
    val isLoading: Boolean = false,
    @Transient
    val courses: List<CourseVO> = listOf(),
    val error: CourseListError? = null,
)

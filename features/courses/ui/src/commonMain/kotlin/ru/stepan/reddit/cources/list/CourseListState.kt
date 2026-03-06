package ru.stepan.reddit.cources.list

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.stepan.reddit.cources.composables.CourseVO

@Serializable
data class CourseListState(
    val isLoading: Boolean = false,
    @Transient
    val courses: List<CourseVO> = listOf(),
    val error: CourseListError? = null,
    val nextPage: Int? = 1
)

enum class CourseListError {
    NETWORK,
    UNKNOWN
}

package ru.stepan.reddit.search.main

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.cources.composables.CourseVO
import ru.stepan.reddit.cources.list.CourseListError

@Serializable
data class MainSearchState(
    val searchQuery: SerializableTextFieldValue = SerializableTextFieldValue.EMPTY,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    @Transient
    val courses: List<CourseVO> = emptyList(),
    val error: CourseListError? = null
)

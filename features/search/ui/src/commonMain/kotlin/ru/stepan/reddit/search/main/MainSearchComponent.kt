package ru.stepan.reddit.search.main

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.decompose.ScreenComponent
import ru.stepan.reddit.cources.list.CourseUIInteraction

interface MainSearchComponent : ScreenComponent<MainSearchState, Unit>, CourseUIInteraction {
    fun onQueryChanged(query: SerializableTextFieldValue)

    fun onSelectCourse(id: Long)

    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onSelectCourse: (Long) -> Unit
        ): MainSearchComponent
    }
}
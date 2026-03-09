package ru.stepan.reddit.search.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class SearchEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onSelectCourse: (Long) -> Unit
        ): SearchEntryComponent
    }
}
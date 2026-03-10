package ru.stepan.reddit.recommendations.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class RecommendationsEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onSelectCourse: (Long) -> Unit
        ): RecommendationsEntryComponent
    }
}
package ru.stepan.reddit.recommendations.main

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent
import ru.stepan.reddit.cources.list.CourseUIInteraction

interface RecommendationsComponent : ScreenComponent<RecommendationsComponentState, Unit>,
    CourseUIInteraction {

    fun onSelectCourse(id: Long)

    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onSelectCourse: (Long) -> Unit
        ): RecommendationsComponent
    }
}
package ru.stepan.reddit.cources.list

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

interface CourseListComponent : ScreenComponent<CourseListState, Unit>, CourseUIInteraction {
    interface Factory {
        fun create(componentContext: ComponentContext, coursesLoader: CoursesLoader): CourseListComponent
    }
}

interface CourseUIInteraction {
    fun onLike(id: Long)

    fun onRefresh()

    fun onLoadNext()
}
package ru.stepan.reddit.course.details.main

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

interface CourseDetailsComponent : ScreenComponent<CourseDetailsState, Unit> {
    interface Factory {
        fun create(componentContext: ComponentContext, courseId: Long): CourseDetailsComponent
    }
}
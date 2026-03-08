package ru.stepan.reddit.course.details.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class CourseDetailsEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(componentContext: ComponentContext, courseId: Long): CourseDetailsEntryComponent
    }
}
package ru.stepan.reddit.course.details.main

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent

class DefaultCourseDetailsComponent(
    componentContext: ComponentContext
) : CourseDetailsComponent,
    BaseScreenComponent<CourseDetailsState, Unit>(
        componentContext,
        CourseDetailsState(),
        CourseDetailsState.serializer()
    ) {
    class Factory : CourseDetailsComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            courseId: Long
        ): CourseDetailsComponent {
            return getKoin().get { parametersOf(componentContext, courseId) }
        }
    }
}
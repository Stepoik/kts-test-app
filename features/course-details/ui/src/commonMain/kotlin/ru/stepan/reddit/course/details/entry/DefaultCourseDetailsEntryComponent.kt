package ru.stepan.reddit.course.details.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.course.details.main.CourseDetailsComponent
import ru.stepan.reddit.course.details.main.CourseDetailsScreen

class DefaultCourseDetailsEntryComponent(
    componentContext: ComponentContext,
    private val courseId: Long,
    private val courseDetailsComponentFactory: CourseDetailsComponent.Factory
) : CourseDetailsEntryComponent(componentContext) {
    private val courseDetailsComponent =
        courseDetailsComponentFactory.create(childContext("course_details"), courseId = courseId)

    @Composable
    override fun Render() {
        CourseDetailsScreen(courseDetailsComponent)
    }

    class Factory : CourseDetailsEntryComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            courseId: Long
        ): CourseDetailsEntryComponent {
            return getKoin().get { parametersOf(componentContext, courseId) }
        }
    }
}
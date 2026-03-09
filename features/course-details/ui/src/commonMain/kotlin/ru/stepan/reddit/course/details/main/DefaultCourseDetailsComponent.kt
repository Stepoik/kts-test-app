package ru.stepan.reddit.course.details.main

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.courses.api.CoursesRepository

class DefaultCourseDetailsComponent(
    componentContext: ComponentContext,
    private val coursesRepository: CoursesRepository,
    private val courseId: Long,
) : CourseDetailsComponent,
    BaseScreenComponent<CourseDetailsState, Unit>(
        componentContext,
        CourseDetailsState(),
        CourseDetailsState.serializer()
    ) {

    init {
        onLoadCourse()
    }

    fun onLoadCourse() {
        if (state.value.isLoading) return

        updateState { it.copy(isLoading = true) }
        componentScope.launch {
            coursesRepository.getCourseById(courseId).onSuccess { course ->
                updateState {
                    it.copy(
                        isLoading = false,
                        course = course.toFullCourseVO()
                    )
                }
            }
        }
    }

    override fun onSaveState(state: CourseDetailsState): CourseDetailsState {
        return state.copy(isLoading = false)
    }

    class Factory : CourseDetailsComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            courseId: Long
        ): CourseDetailsComponent {
            return getKoin().get { parametersOf(componentContext, courseId) }
        }
    }
}
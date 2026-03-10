package ru.stepan.reddit.recommendations.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.cources.list.CourseListComponent
import ru.stepan.reddit.courses.api.CoursesRepository

class DefaultRecommendationsComponent(
    componentContext: ComponentContext,
    courseListComponentFactory: CourseListComponent.Factory,
    private val coursesRepository: CoursesRepository,
    private val onSelectCourse: (Long) -> Unit,
) : RecommendationsComponent, BaseScreenComponent<RecommendationsComponentState, Unit>(
    componentContext,
    RecommendationsComponentState(),
    RecommendationsComponentState.serializer()
) {
    private val courseListComponent =
        courseListComponentFactory.create(childContext("course_list")) {
            coursesRepository.getRecommendations(it)
        }

    init {
        courseListComponent.loadInitial()
        componentScope.launch {
            courseListComponent.state.collect { state ->
                updateState {
                    it.copy(
                        isLoading = state.isLoading,
                        courses = state.courses,
                        error = state.error,
                        isRefreshing = state.isRefreshing
                    )
                }
            }
        }
    }

    override fun onLike(id: Long) = courseListComponent.onLike(id)

    override fun onRefresh() = courseListComponent.onRefresh()

    override fun onLoadNext() = courseListComponent.onLoadNext()

    override fun onSelectCourse(id: Long) = onSelectCourse.invoke(id)

    class Factory : RecommendationsComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onSelectCourse: (Long) -> Unit
        ): RecommendationsComponent {
            return getKoin().get { parametersOf(componentContext, onSelectCourse) }
        }
    }
}
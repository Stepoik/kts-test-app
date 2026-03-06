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
    private val coursesRepository: CoursesRepository
) : RecommendationsComponent, BaseScreenComponent<RecommendationsState, Unit>(
    componentContext,
    RecommendationsState(),
    RecommendationsState.serializer()
) {
    private val courseListComponent =
        courseListComponentFactory.create(childContext("course_list")) {
            coursesRepository.getRecommendations(it)
        }

    init {
        courseListComponent.onRefresh()
        componentScope.launch {
            courseListComponent.state.collect { state ->
                updateState {
                    it.copy(
                        isLoading = state.isLoading,
                        courses = state.courses,
                        error = state.error
                    )
                }
            }
        }
    }

    override fun onLike(id: Long) = courseListComponent.onLike(id)

    override fun onRefresh() = courseListComponent.onRefresh()

    override fun onLoadNext() = courseListComponent.onLoadNext()

    class Factory : RecommendationsComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): RecommendationsComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
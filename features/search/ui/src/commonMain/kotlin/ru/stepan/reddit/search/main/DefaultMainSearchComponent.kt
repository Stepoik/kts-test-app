package ru.stepan.reddit.search.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.text
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.cources.list.CourseListComponent
import ru.stepan.reddit.courses.api.CoursesRepository

@OptIn(FlowPreview::class)
class DefaultMainSearchComponent(
    componentContext: ComponentContext,
    private val courseListComponentFactory: CourseListComponent.Factory,
    private val coursesRepository: CoursesRepository
) : MainSearchComponent, BaseScreenComponent<MainSearchState, Unit>(
    componentContext,
    MainSearchState(),
    MainSearchState.serializer()
) {
    private val courseComponent = courseListComponentFactory.create(childContext("courses")) {
        coursesRepository.findCourses(query = state.value.searchQuery.text, page = it)
    }

    init {
        courseComponent.loadInitial()

        componentScope.launch {
            state.map { it.searchQuery.text }
                .distinctUntilChanged()
                .debounce(DEBOUNCE_MILLIS)
                .collect {
                    courseComponent.loadInitial()
                }
        }

        componentScope.launch {
            courseComponent.state.collect { state ->
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

    override fun onQueryChanged(query: SerializableTextFieldValue) {
        updateState { it.copy(searchQuery = query) }
    }

    override fun onLike(id: Long) = courseComponent.onLike(id)

    override fun onRefresh() = courseComponent.onRefresh()

    override fun onLoadNext() = courseComponent.onLoadNext()

    companion object {
        private const val DEBOUNCE_MILLIS = 300L
    }

    class Factory : MainSearchComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): MainSearchComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
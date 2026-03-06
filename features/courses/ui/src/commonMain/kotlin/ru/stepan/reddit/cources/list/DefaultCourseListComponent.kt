package ru.stepan.reddit.cources.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.api.NetworkError
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.courses.domain.usecases.GetFullCoursesUseCase

class DefaultCourseListComponent(
    componentContext: ComponentContext,
    private val coursesLoader: CoursesLoader,
    private val getFullCoursesUseCase: GetFullCoursesUseCase
) : CourseListComponent, BaseScreenComponent<CourseListState, Unit>(
    componentContext = componentContext,
    initialState = CourseListState(),
    serializer = CourseListState.serializer()
) {
    private var refreshJob: Job? = null
    private var loadNextJob: Job? = null

    override fun onLike(id: Long) {

    }

    override fun onRefresh() {
        refreshJob?.cancel()
        loadNextJob?.cancel()
        updateState { it.copy(isLoading = true) }

        componentScope.launch {
            getFullCoursesUseCase.execute {
                coursesLoader.getCourses(1)
            }.onSuccess { newCourses ->
                updateState {
                    it.copy(
                        isLoading = false,
                        nextPage = newCourses.nextPage,
                        courses = newCourses.courses.map { it.toVO() }
                    )
                }
            }.onFailure { throwable ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = throwable.toError(),
                        courses = emptyList()
                    )
                }
            }
        }
    }

    override fun onLoadNext() {
        if (state.value.isLoading) return

        updateState { it.copy(isLoading = true) }
        loadNextJob?.cancel()
        loadNextJob = componentScope.launch {
            val state = state.value
            val nextPage = state.nextPage ?: return@launch
            getFullCoursesUseCase.execute {
                coursesLoader.getCourses(nextPage)
            }.onSuccess { newCourses ->
                updateState {
                    it.copy(
                        isLoading = false,
                        nextPage = newCourses.nextPage,
                        courses = it.courses + newCourses.courses.map { it.toVO() }
                    )
                }
            }.onFailure { throwable ->
                updateState { it.copy(isLoading = false, error = throwable.toError()) }
            }
        }
    }

    override fun onSaveState(state: CourseListState): CourseListState {
        return state.copy(isLoading = false)
    }

    class Factory: CourseListComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            coursesLoader: CoursesLoader
        ): CourseListComponent {
            return get { parametersOf(componentContext, coursesLoader) }
        }
    }
}

private fun Throwable.toError(): CourseListError {
    return when (this) {
        is NetworkError -> CourseListError.NETWORK
        else -> CourseListError.UNKNOWN
    }
}
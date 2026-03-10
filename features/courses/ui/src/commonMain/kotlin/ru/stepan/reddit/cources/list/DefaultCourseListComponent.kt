package ru.stepan.reddit.cources.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.api.NetworkError
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.courses.api.models.FullCoursePage
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
    private var loadNextJob: Job? = null

    override fun onLike(id: Long) {

    }

    override fun onRefresh() {
        updateState { it.copy(isRefreshing = true, courses = emptyList()) }

        loadCourses(page = 1, replace = false)
    }

    override fun onLoadNext() {
        val state = state.value
        if (state.isLoading) return

        val nextPage = state.nextPage ?: return
        updateState { it.copy(isLoading = true) }

        loadCourses(page = nextPage, replace = false)
    }

    override fun loadInitial() {
        updateState { it.copy(isLoading = true, courses = emptyList()) }

        loadCourses(page = 1, replace = true)
    }

    override fun onSaveState(state: CourseListState): CourseListState {
        return state.copy(isLoading = false, isRefreshing = false)
    }

    private fun loadCourses(page: Int, replace: Boolean) {
        loadNextJob?.cancel()
        loadNextJob = componentScope.launch {
            getFullCoursesUseCase.execute {
                coursesLoader.getCourses(page)
            }.onSuccess {
                onLoaded(it, replace)
            }.onFailure(::onError)
        }
    }

    private fun onLoaded(coursePage: FullCoursePage, replace: Boolean) {
        updateState {
            val voCourses = coursePage.courses.map { it.toVO() }
            val newCourses = if (replace) voCourses else it.courses + voCourses
            it.copy(
                isLoading = false,
                isRefreshing = false,
                nextPage = coursePage.nextPage,
                courses = newCourses
            )
        }
    }

    private fun onError(throwable: Throwable) {
        updateState {
            it.copy(
                isLoading = false,
                isRefreshing = false,
                error = throwable.toError()
            )
        }
    }

    class Factory : CourseListComponent.Factory, KoinComponent {
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
package ru.stepan.reddit.search.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.cources.composables.CourseList
import ru.stepan.reddit.cources.composables.CourseListSkeleton
import ru.stepan.reddit.cources.list.CourseListError
import ru.stepan.reddit.cources.list.toText
import ru.stepan.reddit.uikit.components.RedditOutlinedTextField
import ru.stepan.reddit.uikit.dimens
import testapp.features.search.ui.generated.resources.Res
import testapp.features.search.ui.generated.resources.nothing_found
import testapp.features.search.ui.generated.resources.reload
import testapp.features.search.ui.generated.resources.search

@Composable
fun MainSearchScreen(
    component: MainSearchComponent
) {
    val state = component.state.collectAsState().value

    Scaffold { innerPadding ->
        Column(
            Modifier.padding(innerPadding)
                .padding(MaterialTheme.dimens.paddings.md)
        ) {
            RedditOutlinedTextField(
                value = state.searchQuery,
                onValueChange = component::onQueryChanged,
                singleLine = true,
                placeholder = stringResource(Res.string.search),
                modifier = Modifier.fillMaxWidth()
            )
            SearchCourses(component = component, state = state)
        }
    }
}

@Composable
private fun SearchCourses(component: MainSearchComponent, state: MainSearchState) {
    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = component::onRefresh,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isRefreshing -> CourseListSkeleton(Modifier.fillMaxSize())
            state.error != null && state.courses.isEmpty() -> ErrorInfo(
                state.error,
                onReload = component::onLoadNext,
                modifier = Modifier.fillMaxSize()
            )

            state.isLoading && state.courses.isEmpty() -> CourseListSkeleton(Modifier.fillMaxSize())

            else -> {
                if (state.courses.isEmpty()) {
                    EmptyCourses(
                        onReload = component::onLoadNext,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    CourseList(
                        state.courses,
                        isLoading = state.isLoading,
                        onNext = component::onLoadNext,
                        onLike = component::onLike,
                        onSelectCourse = component::onSelectCourse,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyCourses(onReload: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.dimens.paddings.md,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(stringResource(Res.string.nothing_found))
        Button(onClick = onReload) {
            Text(stringResource(Res.string.reload))
        }
    }
}

@Composable
private fun ErrorInfo(error: CourseListError, onReload: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.dimens.paddings.md,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(error.toText(), style = MaterialTheme.typography.titleLarge)
        Button(onClick = onReload) {
            Text(stringResource(Res.string.reload))
        }
    }
}
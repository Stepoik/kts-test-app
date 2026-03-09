package ru.stepan.reddit.recommendations.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.stepan.reddit.cources.composables.CourseList
import ru.stepan.reddit.cources.composables.CourseListSkeleton
import ru.stepan.reddit.cources.list.CourseListError
import ru.stepan.reddit.cources.list.toText
import ru.stepan.reddit.uikit.dimens

@Composable
fun RecommendationsScreen(component: RecommendationsComponent) {
    val state = component.state.collectAsState().value.asScreenState()

    Scaffold { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = component::onRefresh,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.dimens.paddings.md)
        ) {
            when (state) {
                is RecommendationsScreenState.Loading ->
                    CourseListSkeleton(modifier = Modifier.fillMaxSize())

                is RecommendationsScreenState.Error -> ErrorInfo(
                    error = state.error,
                    modifier = Modifier.fillMaxSize()
                )

                is RecommendationsScreenState.Loaded -> {
                    if (state.isRefreshing) {
                        CourseListSkeleton(modifier = Modifier.fillMaxSize())
                    } else {
                        CourseList(
                            courses = state.courses,
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
}

@Composable
private fun ErrorInfo(error: CourseListError, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text(error.toText(), style = MaterialTheme.typography.titleLarge)
    }
}
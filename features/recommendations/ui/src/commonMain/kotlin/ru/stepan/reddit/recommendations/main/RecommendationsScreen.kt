package ru.stepan.reddit.recommendations.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import ru.stepan.reddit.uikit.dimens
import testapp.features.recommendations.ui.generated.resources.Res
import testapp.features.recommendations.ui.generated.resources.no_recommendations
import testapp.features.recommendations.ui.generated.resources.reload

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
                    onReload = component::onRefresh,
                    modifier = Modifier.fillMaxSize()
                )

                is RecommendationsScreenState.Loaded -> {
                    if (state.isRefreshing) {
                        CourseListSkeleton(modifier = Modifier.fillMaxSize())
                    } else if (state.courses.isEmpty()) {
                        EmptyRecommendations(
                            onReload = component::onRefresh,
                            Modifier.fillMaxSize()
                        )
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
private fun EmptyRecommendations(onReload: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.dimens.paddings.md,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(stringResource(Res.string.no_recommendations))
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
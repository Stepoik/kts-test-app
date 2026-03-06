package ru.stepan.reddit.recommendations.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.stepan.reddit.cources.composables.CourseList

@Composable
fun RecommendationsScreen(component: RecommendationsComponent) {
    val state = component.state.collectAsState().value

    Scaffold { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            if (state.isLoading || state.courses.isEmpty()) {

            } else {
                CourseList(
                    courses = state.courses,
                    onNext = component::onLoadNext,
                    onLike = component::onLike
                )
            }
        }
    }
}
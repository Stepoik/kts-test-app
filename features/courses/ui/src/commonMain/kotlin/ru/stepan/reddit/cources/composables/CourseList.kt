package ru.stepan.reddit.cources.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private const val LOAD_NEXT_THRESHOLD = 5

@Composable
fun CourseList(
    courses: List<CourseVO>,
    isLoading: Boolean,
    onNext: () -> Unit,
    onLike: (id: Long) -> Unit,
    onSelectCourse: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val coursesState = rememberUpdatedState(courses)
    val lazyState = rememberLazyListState()
    LaunchedEffect(Unit) {
        snapshotFlow { coursesState.value.size - (lazyState.layoutInfo.visibleItemsInfo.size + lazyState.firstVisibleItemIndex) }.collect {
            if (it <= LOAD_NEXT_THRESHOLD) {
                onNext()
            }
        }
    }
    LazyColumn(modifier = modifier, state = lazyState) {
        items(courses, key = { it.id }) {
            CourseItem(
                it,
                onLike = { onLike(it.id) },
                onClick = { onSelectCourse(it.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isLoading) {
            item {
                LoadingItem(Modifier.fillMaxWidth())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun LoadingItem(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        LoadingIndicator()
    }
}
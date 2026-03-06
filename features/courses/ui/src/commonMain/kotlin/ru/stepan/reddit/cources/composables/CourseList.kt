package ru.stepan.reddit.cources.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier

private const val LOAD_NEXT_THRESHOLD = 5

@Composable
fun CourseList(courses: List<CourseVO>, onNext: () -> Unit, onLike: (id: Long) -> Unit) {
    val coursesState = rememberUpdatedState(courses)
    val lazyState = rememberLazyListState()
    LaunchedEffect(Unit) {
        snapshotFlow { coursesState.value.size - (lazyState.layoutInfo.visibleItemsInfo.size + lazyState.firstVisibleItemIndex) }.collect {
            if (it <= LOAD_NEXT_THRESHOLD) {
                onNext()
            }
        }
    }
    LazyColumn {
        items(courses, key = { it.id }) {
            CourseItem(it, onLike = { onLike(it.id) }, modifier = Modifier.fillMaxWidth())
        }
    }
}
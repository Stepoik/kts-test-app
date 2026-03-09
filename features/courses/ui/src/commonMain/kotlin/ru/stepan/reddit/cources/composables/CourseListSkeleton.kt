package ru.stepan.reddit.cources.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CourseListSkeleton(modifier: Modifier = Modifier, itemsCount: Int = 10) {
    LazyColumn(modifier = modifier) {
        items(itemsCount) {
            CourseItemSkeleton(Modifier.fillMaxWidth())
        }
    }
}
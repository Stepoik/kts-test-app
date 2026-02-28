package ru.stepan.reddit.feed.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.posts.composables.PostItem
import testapp.features.feed.ui.generated.resources.Res
import testapp.features.feed.ui.generated.resources.feed

private const val LOAD_NEXT_THRESHOLD = 5

@Composable
fun FeedScreen(component: FeedComponent) {
    val postsState = component.postsComponent.state.collectAsState().value

    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        snapshotFlow { component.postsComponent.state.value.posts.size - (lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size) }.collect {
            println(it)
            if (it <= LOAD_NEXT_THRESHOLD) {
                component.postsComponent.onLoadNext()
            }
        }
    }
    Scaffold {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 16.dp)
        ) {
            stickyHeader {
                Box(
                    Modifier.height(32.dp).fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        stringResource(Res.string.feed),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                    )
                }
            }
            items(postsState.posts, key = { it.id }) { post ->
                Column {
                    PostItem(
                        post,
                        onShare = {},
                        onLike = {
                            component.postsComponent.onLikePost(
                                postId = post.id,
                                liked = it
                            )
                        },
                        onComment = {}
                    )
                    HorizontalDivider(Modifier.fillMaxWidth())
                }
            }
        }
    }
}
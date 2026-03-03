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
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.posts.composables.PostItem
import ru.stepan.reddit.posts.entry.PostsComponent
import ru.stepan.reddit.posts.entry.PostsState
import ru.stepan.reddit.uikit.RedditTheme
import ru.stepan.reddit.uikit.dimens
import testapp.features.feed.ui.generated.resources.Res
import testapp.features.feed.ui.generated.resources.feed

private const val LOAD_NEXT_THRESHOLD = 5

@Composable
fun FeedScreen(component: FeedComponent) {
    val postsState = component.postsComponent.state.collectAsState().value

    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        snapshotFlow { component.postsComponent.state.value.posts.size - (lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size) }.collect {
            if (it <= LOAD_NEXT_THRESHOLD) {
                component.postsComponent.onLoadNext()
            }
        }
    }
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = MaterialTheme.dimens.lg),
            state = lazyListState,
        ) {
            stickyHeader {
                Box(
                    Modifier
                        .height(MaterialTheme.dimens.xxxl)
                        .fillMaxWidth()
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
                        post = post,
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

@Preview
@Composable
private fun FeedScreenPreview() {
    val postsComponent = object : PostsComponent {
        override fun onLikePost(postId: String, liked: Boolean) {}
        override fun onLoadNext() {}
        override val state: StateFlow<PostsState> = MutableStateFlow(PostsState())
        override val events: SharedFlow<Unit> = MutableSharedFlow()
    }
    val component = object : FeedComponent {
        override val postsComponent: PostsComponent = postsComponent
        override val events: SharedFlow<Unit> = MutableSharedFlow()
        override val state: StateFlow<Unit> = MutableStateFlow(Unit)
    }
    RedditTheme {
        FeedScreen(component)
    }
}
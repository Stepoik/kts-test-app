package ru.stepan.reddit.posts.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.stepan.reddit.uikit.RedditTheme
import ru.stepan.reddit.uikit.icons.Comment
import ru.stepan.reddit.uikit.icons.DownArrow
import ru.stepan.reddit.uikit.icons.Icons
import ru.stepan.reddit.uikit.icons.Menu
import ru.stepan.reddit.uikit.icons.Share
import ru.stepan.reddit.uikit.icons.UpArrow

@Composable
fun PostItem(
    post: PostItemVO,
    onShare: () -> Unit,
    onComment: () -> Unit,
    onLike: (like: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        PostHeader(post.author, post.time, modifier = Modifier.fillMaxWidth())
        PostBody(post.title, post.selftext, modifier = Modifier.fillMaxWidth())
        PostActions(
            score = post.score,
            liked = post.liked,
            commentsCount = post.commentsCount,
            onShare = onShare,
            onComment = onComment,
            onLike = onLike
        )
    }
}

@Composable
private fun PostHeader(author: String, time: String, modifier: Modifier = Modifier) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(author)
            Text("•")
            Text(time)
        }
        IconButton(onClick = {}) {
            Icon(Icons.Menu, contentDescription = null, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun PostBody(title: String, text: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(title)
        Text(text)
    }
}

@Composable
private fun PostActions(
    score: Int,
    liked: Boolean?,
    commentsCount: Int,
    onShare: () -> Unit,
    onComment: () -> Unit,
    onLike: (like: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val iconModifier = Modifier.size(16.dp)
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(Modifier) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val likedTint = if (liked == true) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
                val dislikedTint = if (liked == false) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
                IconButton(onClick = { onLike(true) }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        Icons.UpArrow,
                        contentDescription = null,
                        modifier = iconModifier,
                        tint = likedTint
                    )
                }
                Text(score.toString())
                IconButton(onClick = { onLike(false) }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        Icons.DownArrow,
                        contentDescription = null,
                        modifier = iconModifier,
                        tint = dislikedTint
                    )
                }
            }
        }

        Card(onClick = onComment) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    Icons.Comment,
                    contentDescription = null,
                    modifier = iconModifier
                )
                Text(commentsCount.toString())
            }
        }

        Card(onClick = onShare) {
            Box(Modifier.padding(horizontal = 16.dp), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Share,
                    contentDescription = null,
                    modifier = iconModifier
                )
                Text("")
            }
        }
    }
}

data class PostItemVO(
    val id: String,
    val name: String,
    val title: String,
    val selftext: String,
    val time: String,
    val commentsCount: Int,
    val author: String,
    val ups: Int,
    val downs: Int,
    val score: Int,
    val liked: Boolean?
)

@Preview
@Composable
private fun PostItemPreview() {
    val post = PostItemVO("", "Name", "Title", "lorem ipsum", "3 ч", 67, "Author", 6, 2, 4, false)
    RedditTheme {
        PostItem(post = post, onLike = {}, onComment = {}, onShare = {})
    }
}
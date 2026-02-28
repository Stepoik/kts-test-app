package ru.stepan.reddit.posts.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

interface PostsComponent : ScreenComponent<PostsState, Unit> {
    fun onLikePost(postId: String, liked: Boolean)

    fun onLoadNext()

    interface Factory {
        fun create(componentContext: ComponentContext, postsLoader: PostsLoader): PostsComponent
    }
}
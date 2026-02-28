package ru.stepan.reddit.feed.feed

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent
import ru.stepan.reddit.posts.entry.PostsComponent

interface FeedComponent : ScreenComponent<Unit, Unit> {
    val postsComponent: PostsComponent

    interface Factory {
        fun create(componentContext: ComponentContext): FeedComponent
    }
}
package ru.stepan.reddit.feed.feed

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import kotlinx.serialization.builtins.serializer
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.posts.api.PostsRepository
import ru.stepan.reddit.posts.entry.PostsComponent

class DefaultFeedComponent(
    componentContext: ComponentContext,
    private val postsComponentFactory: PostsComponent.Factory,
    private val postsRepository: PostsRepository
) : FeedComponent, BaseScreenComponent<Unit, Unit>(componentContext, Unit, Unit.serializer()) {

    override val postsComponent: PostsComponent =
        postsComponentFactory.create(
            childContext("posts"),
            postsLoader = postsRepository::getPosts
        )

    class Factory : FeedComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): FeedComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
package ru.stepan.reddit.feed.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.feed.feed.FeedComponent
import ru.stepan.reddit.feed.feed.FeedScreen
import ru.stepan.reddit.main.entry.FeedEntryComponent

class DefaultFeedEntryComponent(
    componentContext: ComponentContext,
    private val feedComponentFactory: FeedComponent.Factory
) : FeedEntryComponent(componentContext) {
    private val feedComponent = feedComponentFactory.create(childContext("feed"))

    @Composable
    override fun Render() {
        FeedScreen(feedComponent)
    }

    class Factory : FeedEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): FeedEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
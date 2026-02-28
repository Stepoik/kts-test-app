package ru.stepan.reddit.home.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.main.entry.FeedEntryComponent

class DefaultHomeEntryComponent(
    componentContext: ComponentContext,
    private val feedComponentFactory: FeedEntryComponent.Factory
) : HomeEntryComponent(componentContext) {
    private val feedComponent = feedComponentFactory.create(componentContext.childContext("feed"))

    @Composable
    override fun Render() {
        feedComponent.Render()
    }

    class Factory : HomeEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): HomeEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
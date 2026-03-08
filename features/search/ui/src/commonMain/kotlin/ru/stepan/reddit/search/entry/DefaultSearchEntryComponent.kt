package ru.stepan.reddit.search.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.search.main.MainSearchComponent
import ru.stepan.reddit.search.main.MainSearchScreen

class DefaultSearchEntryComponent(
    componentContext: ComponentContext,
    private val searchComponentFactory: MainSearchComponent.Factory
) : SearchEntryComponent(componentContext) {
    private val searchComponent = searchComponentFactory.create(childContext("main"))

    @Composable
    override fun Render() {
        MainSearchScreen(searchComponent)
    }

    class Factory : SearchEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): SearchEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
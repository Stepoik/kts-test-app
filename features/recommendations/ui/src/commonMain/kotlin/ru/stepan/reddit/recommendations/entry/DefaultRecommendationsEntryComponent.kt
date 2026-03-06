package ru.stepan.reddit.recommendations.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.recommendations.main.RecommendationsComponent
import ru.stepan.reddit.recommendations.main.RecommendationsScreen

class DefaultRecommendationsEntryComponent(
    componentContext: ComponentContext,
    private val recommendationsComponentFactory: RecommendationsComponent.Factory
) : RecommendationsEntryComponent(componentContext) {
    private val recommendationsComponent = recommendationsComponentFactory.create(childContext("recommendations"))

    @Composable
    override fun Render() {
        RecommendationsScreen(recommendationsComponent)
    }

    class Factory : RecommendationsEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): RecommendationsEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
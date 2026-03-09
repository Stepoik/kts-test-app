package ru.stepan.reddit.recommendations

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.recommendations.entry.DefaultRecommendationsEntryComponent
import ru.stepan.reddit.recommendations.entry.RecommendationsEntryComponent
import ru.stepan.reddit.recommendations.main.DefaultRecommendationsComponent
import ru.stepan.reddit.recommendations.main.RecommendationsComponent

val recommendationsUiModule = module {
    factory<RecommendationsComponent.Factory> { DefaultRecommendationsComponent.Factory() }
    factoryOf(::DefaultRecommendationsComponent) bind RecommendationsComponent::class

    factory<RecommendationsEntryComponent.Factory> { DefaultRecommendationsEntryComponent.Factory() }
    factoryOf(::DefaultRecommendationsEntryComponent) bind RecommendationsEntryComponent::class
}
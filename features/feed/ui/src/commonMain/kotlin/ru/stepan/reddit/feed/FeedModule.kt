package ru.stepan.reddit.feed

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.feed.entry.DefaultFeedEntryComponent
import ru.stepan.reddit.feed.feed.DefaultFeedComponent
import ru.stepan.reddit.feed.feed.FeedComponent
import ru.stepan.reddit.main.entry.FeedEntryComponent

val feedModule = module {
    factoryOf(::DefaultFeedComponent) bind FeedComponent::class
    factory<FeedComponent.Factory> { DefaultFeedComponent.Factory() }

    factoryOf(::DefaultFeedEntryComponent) bind FeedEntryComponent::class
    factory<FeedEntryComponent.Factory> { DefaultFeedEntryComponent.Factory() }
}
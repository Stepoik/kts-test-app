package ru.stepan.reddit.search

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.search.entry.DefaultSearchEntryComponent
import ru.stepan.reddit.search.entry.SearchEntryComponent
import ru.stepan.reddit.search.main.DefaultMainSearchComponent
import ru.stepan.reddit.search.main.MainSearchComponent

val searchUiModule = module {
    factoryOf(::DefaultMainSearchComponent) bind MainSearchComponent::class
    factory<MainSearchComponent.Factory> { DefaultMainSearchComponent.Factory() }

    factoryOf(::DefaultSearchEntryComponent) bind SearchEntryComponent::class
    factory<SearchEntryComponent.Factory> { DefaultSearchEntryComponent.Factory() }
}
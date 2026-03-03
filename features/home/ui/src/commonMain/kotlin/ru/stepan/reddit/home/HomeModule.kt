package ru.stepan.reddit.home

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.home.entry.DefaultHomeEntryComponent
import ru.stepan.reddit.home.entry.HomeEntryComponent

val homeModule = module {
    factoryOf(::DefaultHomeEntryComponent) bind HomeEntryComponent::class
    factory<HomeEntryComponent.Factory> { DefaultHomeEntryComponent.Factory() }
}
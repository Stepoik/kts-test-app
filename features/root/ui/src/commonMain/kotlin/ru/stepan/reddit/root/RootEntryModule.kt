package ru.stepan.reddit.root

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.root.entry.DefaultRootEntryComponent
import ru.stepan.reddit.root.entry.RootEntryComponent

val rootEntryModule = module {
    factoryOf(::DefaultRootEntryComponent) bind RootEntryComponent::class
    factory<RootEntryComponent.Factory> { DefaultRootEntryComponent.Factory() }
}
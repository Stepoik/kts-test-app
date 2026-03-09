package ru.stepan.reddit.splash

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.splash.entry.DefaultSplashEntryComponent
import ru.stepan.reddit.splash.entry.SplashEntryComponent

val splashModule = module {
    factoryOf(::DefaultSplashEntryComponent) bind SplashEntryComponent::class
    factory<SplashEntryComponent.Factory> { DefaultSplashEntryComponent.Factory() }
}
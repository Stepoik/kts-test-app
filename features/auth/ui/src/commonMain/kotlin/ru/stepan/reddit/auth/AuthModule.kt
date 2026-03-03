package ru.stepan.reddit.auth

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.auth.entry.AuthEntryComponent
import ru.stepan.reddit.auth.entry.DefaultAuthEntryComponent
import ru.stepan.reddit.auth.login.DefaultLoginComponent
import ru.stepan.reddit.auth.login.LoginComponent

val authModule = module {
    factoryOf(::DefaultLoginComponent) bind LoginComponent::class
    factory<LoginComponent.Factory> { DefaultLoginComponent.Factory() }

    factoryOf(::DefaultAuthEntryComponent) bind AuthEntryComponent::class
    factory<AuthEntryComponent.Factory> { DefaultAuthEntryComponent.Factory() }
}
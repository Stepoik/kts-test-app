package ru.stepan.reddit.core.data

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.stepan.reddit.core.data.network.StepikOAuthClientFactory
import ru.stepan.reddit.core.data.network.authorizedKtorClient
import ru.stepan.reddit.core.data.network.defaultKtorClient

internal expect val platformModule: Module

object CoreQualifiers {
    val AUTHORIZED_KTOR_CLIENT = named("AUTHORIZED_KTOR_CLIENT")
    val DEFAULT_KTOR_CLIENT = named("DEFAULT_KTOR_CLIENT")
}

val coreDataModule = module {
    single { StepikOAuthClientFactory() }

    single(CoreQualifiers.AUTHORIZED_KTOR_CLIENT) { authorizedKtorClient(get()) }
    single(CoreQualifiers.DEFAULT_KTOR_CLIENT) { defaultKtorClient() }

    includes(platformModule)
}
package ru.stepan.reddit.auth

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.auth.api.AccountRepository
import ru.stepan.reddit.auth.api.AccountRepositoryImpl

val authDataModule = module {
    singleOf(::AccountRepositoryImpl) bind AccountRepository::class
}
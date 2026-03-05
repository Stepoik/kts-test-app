package ru.stepan.reddit.auth

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.auth.api.AccountRepository
import ru.stepan.reddit.auth.api.AccountRepositoryImpl
import ru.stepan.reddit.auth.network.AccountKtorDatastore
import ru.stepan.reddit.core.data.CoreQualifiers

val authDataModule = module {
    single<AccountRepository> {
        AccountRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    single<AccountKtorDatastore> { AccountKtorDatastore(get(CoreQualifiers.AUTHORIZED_KTOR_CLIENT)) }
}
package ru.stepan.reddit.core.data

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.stepan.reddit.core.data.datastore.dataStore

internal actual val platformModule: Module = module {
    single { dataStore(get()) }
}
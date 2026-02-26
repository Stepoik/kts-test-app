package ru.stepan.reddit.umbrella

import org.koin.dsl.module
import ru.stepan.reddit.auth.authDataModule
import ru.stepan.reddit.auth.authModule
import ru.stepan.reddit.root.rootEntryModule

val umbrellaModule = module {
    includes(authModule, authDataModule, rootEntryModule)
}
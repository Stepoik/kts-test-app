package ru.stepan.reddit.core.data

import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val platformModule: Module

val coreDataModule = module {
    includes(platformModule)
}
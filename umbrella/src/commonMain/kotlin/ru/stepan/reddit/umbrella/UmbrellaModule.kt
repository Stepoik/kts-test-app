package ru.stepan.reddit.umbrella

import org.koin.dsl.module
import ru.stepan.reddit.auth.authDataModule
import ru.stepan.reddit.auth.authModule
import ru.stepan.reddit.core.data.coreDataModule
import ru.stepan.reddit.feed.feedModule
import ru.stepan.reddit.home.homeModule
import ru.stepan.reddit.onboarding.onboardingDataModule
import ru.stepan.reddit.onboarding.onboardingUiModule
import ru.stepan.reddit.posts.postsDataModule
import ru.stepan.reddit.posts.postsUiModule
import ru.stepan.reddit.root.rootEntryModule
import ru.stepan.reddit.splash.splashModule

val umbrellaModule = module {
    includes(
        authModule,
        authDataModule,
        rootEntryModule,
        onboardingUiModule,
        onboardingDataModule,
        splashModule,
        feedModule,
        homeModule,
        postsUiModule,
        postsDataModule,
        coreDataModule
    )
}
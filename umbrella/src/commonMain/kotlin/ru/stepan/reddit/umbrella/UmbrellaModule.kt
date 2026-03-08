package ru.stepan.reddit.umbrella

import org.koin.dsl.module
import ru.stepan.reddit.auth.authModule
import ru.stepan.reddit.core.data.coreDataModule
import ru.stepan.reddit.cources.coursesUiModule
import ru.stepan.reddit.course.details.courseDetailsUiModule
import ru.stepan.reddit.courses.api.coursesDataModule
import ru.stepan.reddit.home.homeModule
import ru.stepan.reddit.onboarding.onboardingDataModule
import ru.stepan.reddit.onboarding.onboardingUiModule
import ru.stepan.reddit.recommendations.recommendationsUiModule
import ru.stepan.reddit.root.rootEntryModule
import ru.stepan.reddit.search.searchUiModule
import ru.stepan.reddit.splash.splashModule

val umbrellaModule = module {
    includes(
        authModule,
        rootEntryModule,
        onboardingUiModule,
        onboardingDataModule,
        splashModule,
        homeModule,
        coursesUiModule,
        coursesDataModule,
        recommendationsUiModule,
        searchUiModule,
        courseDetailsUiModule,
        coreDataModule
    )
}
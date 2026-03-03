package ru.stepan.reddit.onboarding

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.onboarding.api.OnboardingRepository
import ru.stepan.reddit.onboarding.api.OnboardingRepositoryImpl

val onboardingDataModule = module {
    singleOf(::OnboardingRepositoryImpl) bind OnboardingRepository::class
}
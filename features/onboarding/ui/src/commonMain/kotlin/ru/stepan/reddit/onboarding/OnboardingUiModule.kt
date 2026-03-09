package ru.stepan.reddit.onboarding

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.onboarding.app.AppOnboardingComponent
import ru.stepan.reddit.onboarding.app.DefaultAppOnboardingComponent
import ru.stepan.reddit.onboarding.entry.DefaultOnboardingEntryComponent
import ru.stepan.reddit.onboarding.entry.OnboardingEntryComponent

val onboardingUiModule = module {
    factoryOf(::DefaultOnboardingEntryComponent) bind OnboardingEntryComponent::class
    factory<OnboardingEntryComponent.Factory> { DefaultOnboardingEntryComponent.Factory() }

    factoryOf(::DefaultAppOnboardingComponent) bind AppOnboardingComponent::class
    factory<AppOnboardingComponent.Factory> { DefaultAppOnboardingComponent.Factory() }
}
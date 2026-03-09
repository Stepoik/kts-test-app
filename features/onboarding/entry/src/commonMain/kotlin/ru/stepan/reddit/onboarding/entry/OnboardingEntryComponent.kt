package ru.stepan.reddit.onboarding.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class OnboardingEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onStartApp: () -> Unit
        ): OnboardingEntryComponent
    }
}
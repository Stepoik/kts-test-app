package ru.stepan.reddit.onboarding.app

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

interface AppOnboardingComponent : ScreenComponent<Unit, Unit> {
    fun onStartButtonClicked()

    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onStartApp: () -> Unit
        ): AppOnboardingComponent
    }
}
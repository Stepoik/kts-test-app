package ru.stepan.reddit.splash.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class SplashEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(
            componentContext: ComponentContext,
            onNavigateHome: () -> Unit,
            onNavigateAuth: () -> Unit
        ): SplashEntryComponent
    }
}
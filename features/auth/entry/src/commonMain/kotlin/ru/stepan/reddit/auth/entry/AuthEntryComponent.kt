package ru.stepan.reddit.auth.entry

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.decompose.EntryComponent

abstract class AuthEntryComponent(
    componentContext: ComponentContext
) : EntryComponent(componentContext) {
    interface Factory {
        fun create(componentContext: ComponentContext, onAuthorized: () -> Unit): AuthEntryComponent
    }
}
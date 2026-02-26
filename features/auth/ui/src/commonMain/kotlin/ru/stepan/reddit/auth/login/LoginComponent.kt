package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

internal interface LoginComponent : ScreenComponent<LoginScreenState, Unit> {
    fun onUsernameChanged(username: SerializableTextFieldValue)

    fun onPasswordChanged(password: SerializableTextFieldValue)

    interface Factory {
        fun create(componentContext: ComponentContext, onAuthorized: () -> Unit): LoginComponent
    }
}
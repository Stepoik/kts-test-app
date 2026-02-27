package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.decompose.ScreenComponent

internal interface LoginComponent : ScreenComponent<LoginScreenState, LoginScreenEvent> {
    val onAuthorized: () -> Unit

    fun onUsernameChanged(username: SerializableTextFieldValue)

    fun onPasswordChanged(password: SerializableTextFieldValue)

    fun onButtonClicked()

    interface Factory {
        fun create(componentContext: ComponentContext, onAuthorized: () -> Unit): LoginComponent
    }
}
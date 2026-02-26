package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent

internal class DefaultLoginComponent(
    componentContext: ComponentContext,
    onAuthorized: () -> Unit
) : BaseScreenComponent<LoginScreenState, Unit>(
    componentContext = componentContext,
    initialState = LoginScreenState(),
    serializer = LoginScreenState.serializer()
), LoginComponent {
    override fun onUsernameChanged(username: SerializableTextFieldValue) {
        updateState { it.copy(username = username) }
    }

    override fun onPasswordChanged(password: SerializableTextFieldValue) {
        updateState { it.copy(password = password) }
    }

    class Factory : LoginComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onAuthorized: () -> Unit
        ): LoginComponent {
            return getKoin().get { parametersOf(componentContext, onAuthorized) }
        }
    }
}
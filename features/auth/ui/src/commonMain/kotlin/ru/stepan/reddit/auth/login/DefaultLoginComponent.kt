package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.api.AccountRepository
import ru.stepan.reddit.auth.api.errors.IncorrectCredentialsError
import ru.stepan.reddit.auth.api.models.AuthCredentials
import ru.stepan.reddit.core.api.NetworkError
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.text
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent

internal class DefaultLoginComponent(
    componentContext: ComponentContext,
    override val onAuthorized: () -> Unit,
    private val accountRepository: AccountRepository
) : BaseScreenComponent<LoginScreenState, LoginScreenEvent>(
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

    override fun onButtonClicked() {
        if (state.value.isLoading) return

        updateState { it.copy(isLoading = true) }
        componentScope.launch {
            val state = state.value
            if (!state.isValid()) {
                updateState { it.copy(isLoading = false) }
                return@launch
            }

            val credentials = state.asCredentials()
            accountRepository.login(credentials)
                .onSuccess {
                    sendEvent(LoginScreenEvent.Authorized())
                    updateState { it.copy(isLoading = false) }
                }.onFailure { error ->
                    updateState { it.copy(error = error.toScreenError(), isLoading = false) }
                }
        }
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

private fun Throwable.toScreenError(): LoginScreenError {
    return when (this) {
        is IncorrectCredentialsError -> LoginScreenError.INCORRECT_CREDENTIALS
        is NetworkError -> LoginScreenError.NETWORK
        else -> LoginScreenError.UNKNOWN
    }
}

private fun LoginScreenState.asCredentials(): AuthCredentials {
    return AuthCredentials(
        username = username.text,
        password = password.text
    )
}

private fun LoginScreenState.isValid(): Boolean {
    return username.text.isNotEmpty() && password.text.isNotEmpty()
}
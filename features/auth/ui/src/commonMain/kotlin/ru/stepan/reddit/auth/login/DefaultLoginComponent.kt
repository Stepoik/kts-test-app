package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.api.AccountRepository
import ru.stepan.reddit.auth.api.errors.IncorrectCredentialsError
import ru.stepan.reddit.core.api.NetworkError
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
    override fun onButtonClicked() {
        if (state.value.isLoading) return

        componentScope.launch {
            val initiation = accountRepository.getAuthInitiation()
            sendEvent(LoginScreenEvent.AuthFlowInitiation(initiation))
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
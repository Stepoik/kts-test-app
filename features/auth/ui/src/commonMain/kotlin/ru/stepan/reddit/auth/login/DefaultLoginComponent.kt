package ru.stepan.reddit.auth.login

import com.arkivanov.decompose.ComponentContext
import dev.lokksmith.client.request.flow.AuthFlow
import dev.lokksmith.client.request.flow.AuthFlowResultProvider
import dev.lokksmith.client.request.flow.authFlowResult
import dev.lokksmith.client.request.flow.authorizationCode.AuthorizationCodeFlow
import dev.lokksmith.client.request.parameter.Scope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.data.network.StepikOAuthClientFactory
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent

internal class DefaultLoginComponent(
    componentContext: ComponentContext,
    override val onAuthorized: () -> Unit,
    private val stepikOAuthClientFactory: StepikOAuthClientFactory
) : BaseScreenComponent<LoginScreenState, LoginScreenEvent>(
    componentContext = componentContext,
    initialState = LoginScreenState(),
    serializer = LoginScreenState.serializer()
), LoginComponent {
    init {
        componentScope.launch {
            stepikOAuthClientFactory.getOrCreate().authFlowResult.collect(::onResult)
        }
    }

    override fun onButtonClicked() {
        if (state.value.status != LoginStatus.WAITING_FOR_LOGIN) return

        updateState { it.copy(status = LoginStatus.LOGGING) }
        componentScope.launch {
            val initiation = getAuthInitiation()
            sendEvent(LoginScreenEvent.AuthFlowInitiation(initiation))
        }
    }

    private fun onResult(result: AuthFlowResultProvider.Result) {
        when (result) {
            is AuthFlowResultProvider.Result.Success -> {
                onAuthorized()
                updateState { it.copy(status = LoginStatus.WAITING_FOR_LOGIN) }
            }

            is AuthFlowResultProvider.Result.Error -> {
                updateState { it.copy(status = LoginStatus.WAITING_FOR_LOGIN) }
            }

            else -> {}
        }
    }

    private suspend fun getAuthInitiation(): AuthFlow.Initiation {
        val request = AuthorizationCodeFlow.Request(
            redirectUri = StepikOAuthClientFactory.REDIRECT_URL,
            scope = setOf(Scope.Write, Scope.Read)
        )
        return stepikOAuthClientFactory.getOrCreate()
            .authorizationCodeFlow(request = request)
            .prepare()
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
package ru.stepan.reddit.auth.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.login.LoginComponent
import ru.stepan.reddit.auth.login.LoginScreen

internal class DefaultAuthEntryComponent(
    componentContext: ComponentContext,
    private val onAuthorized: () -> Unit,
    private val loginComponentFactory: LoginComponent.Factory
) : AuthEntryComponent(componentContext) {
    private val loginComponent = loginComponentFactory.create(
        componentContext.childContext("login"),
        onAuthorized = onAuthorized
    )

    @Composable
    override fun Render() {
        LoginScreen(loginComponent)
    }

    class Factory : AuthEntryComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onAuthorized: () -> Unit
        ): AuthEntryComponent {
            return getKoin().get { parametersOf(componentContext, onAuthorized) }
        }
    }
}
package ru.stepan.reddit.root.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.entry.AuthEntryComponent

class DefaultRootEntryComponent(
    componentContext: ComponentContext,
    private val authComponentFactory: AuthEntryComponent.Factory
) : RootEntryComponent(
    componentContext = componentContext
) {
    private val navigation = StackNavigation<Config>()
    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Splash(),
        childFactory = ::child
    )

    private fun child(config: Config, context: ComponentContext): Child {
        return when (config) {
            is Config.Splash -> Child.Auth(
                authComponentFactory.create(
                    componentContext = context,
                    onAuthorized = {}
                )
            )

            is Config.Auth -> Child.Auth(
                authComponentFactory.create(
                    componentContext = context,
                    onAuthorized = {}
                )
            )
            is Config.Onboarding -> Child.Auth(
                authComponentFactory.create(
                    componentContext = context,
                    onAuthorized = {}
                )
            )
            is Config.Home -> Child.Auth(
                authComponentFactory.create(
                    componentContext = context,
                    onAuthorized = {}
                )
            )
        }
    }

    @Composable
    override fun Render() {
        Children(stack) {
            when (val instance = it.instance) {
                is Child.Auth -> instance.component.Render()
            }
        }
    }

    private sealed class Child {
        data class Auth(val component: AuthEntryComponent) : Child()
    }

    @Serializable
    private sealed class Config {
        @Serializable
        class Splash : Config()

        @Serializable
        class Auth : Config()

        @Serializable
        class Onboarding : Config()

        @Serializable
        class Home : Config()


    }

    class Factory : RootEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): RootEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}
package ru.stepan.reddit.root.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.entry.AuthEntryComponent
import ru.stepan.reddit.core.data.network.StepikOAuthClientFactory
import ru.stepan.reddit.home.entry.HomeEntryComponent
import ru.stepan.reddit.onboarding.api.OnboardingRepository
import ru.stepan.reddit.onboarding.api.models.OnboardingStatus
import ru.stepan.reddit.onboarding.api.models.OnboardingType
import ru.stepan.reddit.onboarding.entry.OnboardingEntryComponent
import ru.stepan.reddit.splash.entry.SplashEntryComponent

class DefaultRootEntryComponent(
    componentContext: ComponentContext,
    private val authComponentFactory: AuthEntryComponent.Factory,
    private val splashComponentFactory: SplashEntryComponent.Factory,
    private val onboardingComponentFactory: OnboardingEntryComponent.Factory,
    private val homeComponentFactory: HomeEntryComponent.Factory,
    private val onboardingRepository: OnboardingRepository,
    private val stepikOAuthClientFactory: StepikOAuthClientFactory
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

    init {
        componentScope.launch {
            stepikOAuthClientFactory.getOrCreate().tokens.collect {
                if (it == null && stack.active.instance is Child.Home) {
                    navigation.replaceAll(Config.Auth())
                }
            }
        }
    }

    private fun child(config: Config, context: ComponentContext): Child {
        return when (config) {
            is Config.Splash -> Child.Splash(
                splashComponentFactory.create(
                    componentContext = context,
                    onNavigateHome = {
                        navigation.replaceAll(Config.Home())
                    },
                    onNavigateAuth = {
                        componentScope.launch {
                            val status =
                                onboardingRepository.getOnboardingStatus(OnboardingType.APP_ONBOARDING)
                                    .getOrDefault(OnboardingStatus.NOT_SHOWN)
                            when (status) {
                                OnboardingStatus.NOT_SHOWN -> navigation.replaceAll(Config.Onboarding())
                                OnboardingStatus.SHOWED -> navigation.replaceAll(Config.Auth())
                            }
                        }
                    }
                )
            )

            is Config.Auth -> Child.Auth(
                authComponentFactory.create(
                    componentContext = context,
                    onAuthorized = {
                        navigation.replaceAll(Config.Home())
                    }
                )
            )

            is Config.Onboarding -> Child.Onboarding(
                onboardingComponentFactory.create(
                    componentContext = context,
                    onStartApp = {
                        navigation.replaceAll(Config.Auth())
                    }
                )
            )

            is Config.Home -> Child.Home(
                homeComponentFactory.create(componentContext = context)
            )
        }
    }

    @Composable
    override fun Render() {
        Children(stack) {
            when (val instance = it.instance) {
                is Child.Auth -> instance.component.Render()
                is Child.Splash -> instance.component.Render()
                is Child.Home -> instance.component.Render()
                is Child.Onboarding -> instance.component.Render()
            }
        }
    }

    private sealed class Child {
        data class Auth(val component: AuthEntryComponent) : Child()
        data class Splash(val component: SplashEntryComponent) : Child()
        data class Home(val component: HomeEntryComponent) : Child()
        data class Onboarding(val component: OnboardingEntryComponent) : Child()
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
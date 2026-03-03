package ru.stepan.reddit.onboarding.entry

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.onboarding.app.AppOnboardingComponent
import ru.stepan.reddit.onboarding.app.AppOnboardingScreen

class DefaultOnboardingEntryComponent(
    componentContext: ComponentContext,
    private val onStartApp: () -> Unit,
    private val appOnboardingFactory: AppOnboardingComponent.Factory
) : OnboardingEntryComponent(componentContext) {

    private val appOnboardingComponent = appOnboardingFactory.create(childContext("app"), onStartApp)

    @Composable
    override fun Render() {
        AppOnboardingScreen(appOnboardingComponent)
    }

    class Factory : OnboardingEntryComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onStartApp: () -> Unit
        ): OnboardingEntryComponent {
            return getKoin().get { parametersOf(componentContext, onStartApp) }
        }
    }
}
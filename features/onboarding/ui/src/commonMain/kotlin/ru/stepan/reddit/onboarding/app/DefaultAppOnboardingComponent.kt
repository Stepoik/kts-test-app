package ru.stepan.reddit.onboarding.app

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.onboarding.api.OnboardingRepository
import ru.stepan.reddit.onboarding.api.models.OnboardingStatus
import ru.stepan.reddit.onboarding.api.models.OnboardingType

class DefaultAppOnboardingComponent(
    componentContext: ComponentContext,
    private val onboardingRepository: OnboardingRepository,
    private val onStartApp: () -> Unit
) : AppOnboardingComponent,
    BaseScreenComponent<Unit, Unit>(componentContext, Unit, Unit.serializer()) {
    override fun onStartButtonClicked() {
        componentScope.launch {
            onboardingRepository.setOnboardingStatus(
                onboarding = OnboardingType.APP_ONBOARDING,
                status = OnboardingStatus.SHOWED
            )
            onStartApp.invoke()
        }
    }

    class Factory : AppOnboardingComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onStartApp: () -> Unit
        ): AppOnboardingComponent {
            return getKoin().get { parametersOf(componentContext, onStartApp) }
        }
    }
}
package ru.stepan.reddit.onboarding.api

import ru.stepan.reddit.onboarding.api.models.OnboardingStatus
import ru.stepan.reddit.onboarding.api.models.OnboardingType

interface OnboardingRepository {
    suspend fun getOnboardingStatus(onboarding: OnboardingType): Result<OnboardingStatus>

    suspend fun setOnboardingStatus(onboarding: OnboardingType, status: OnboardingStatus): Result<Unit>
}
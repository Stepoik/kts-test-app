package ru.stepan.reddit.onboarding.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import ru.stepan.reddit.core.common.result.coroutinesRunCatching
import ru.stepan.reddit.onboarding.api.models.OnboardingStatus
import ru.stepan.reddit.onboarding.api.models.OnboardingType

class OnboardingRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {
    override suspend fun getOnboardingStatus(onboarding: OnboardingType): Result<OnboardingStatus> {
        return coroutinesRunCatching {
            dataStore.data.first()[onboarding.toKey()]?.asStatus()!!
        }
    }

    override suspend fun setOnboardingStatus(
        onboarding: OnboardingType,
        status: OnboardingStatus
    ): Result<Unit> {
        return coroutinesRunCatching {
            dataStore.edit {
                it[onboarding.toKey()] = status.toBoolean()
            }
        }
    }
}

private fun Boolean.asStatus(): OnboardingStatus {
    return when (this) {
        true -> OnboardingStatus.SHOWED
        false -> OnboardingStatus.NOT_SHOWN
    }
}

private fun OnboardingStatus.toBoolean(): Boolean {
    return when (this) {
        OnboardingStatus.SHOWED -> true
        OnboardingStatus.NOT_SHOWN -> false
    }
}

private fun OnboardingType.toKey(): Preferences.Key<Boolean> {
    return when (this) {
        OnboardingType.APP_ONBOARDING -> booleanPreferencesKey("APP_ONBOARDING_STATUS")
    }
}
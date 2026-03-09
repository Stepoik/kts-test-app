package ru.stepan.reddit.auth.login

import dev.lokksmith.client.request.flow.AuthFlow

sealed interface LoginScreenEvent {
    data class AuthFlowInitiation(val initiation: AuthFlow.Initiation) : LoginScreenEvent
}
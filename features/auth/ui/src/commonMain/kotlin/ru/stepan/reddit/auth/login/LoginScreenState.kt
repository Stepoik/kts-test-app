package ru.stepan.reddit.auth.login

import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.text

@Serializable
internal data class LoginScreenState(
    val status: LoginStatus = LoginStatus.WAITING_FOR_LOGIN
)

enum class LoginStatus {
    WAITING_FOR_LOGIN,
    LOGGING
}

internal val LoginScreenState.isButtonEnabled get() = status == LoginStatus.WAITING_FOR_LOGIN
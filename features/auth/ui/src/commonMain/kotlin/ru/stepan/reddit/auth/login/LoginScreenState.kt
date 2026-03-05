package ru.stepan.reddit.auth.login

import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.text

@Serializable
internal data class LoginScreenState(
    val isLoading: Boolean = false,
    val error: LoginScreenError? = null
)

internal val LoginScreenState.isButtonEnabled get() = !isLoading

internal enum class LoginScreenError {
    INCORRECT_CREDENTIALS,
    NETWORK,
    UNKNOWN
}
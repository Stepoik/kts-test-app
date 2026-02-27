package ru.stepan.reddit.auth.login

import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.text

@Serializable
internal data class LoginScreenState(
    val username: SerializableTextFieldValue = SerializableTextFieldValue.EMPTY,
    val password: SerializableTextFieldValue = SerializableTextFieldValue.EMPTY,
    val isLoading: Boolean = false,
    val error: LoginScreenError? = null
)

internal val LoginScreenState.isButtonEnabled get() = username.text.isNotEmpty() && password.text.isNotEmpty()

internal enum class LoginScreenError {
    INCORRECT_CREDENTIALS,
    NETWORK,
    UNKNOWN
}

package ru.stepan.reddit.auth.login

import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue

@Serializable
internal data class LoginScreenState(
    val username: SerializableTextFieldValue = SerializableTextFieldValue.EMPTY,
    val password: SerializableTextFieldValue = SerializableTextFieldValue.EMPTY,
    val error: LoginScreenError? = null
)

internal enum class LoginScreenError {
    INCORRECT_CREDENTIALS,
    NETWORK_ERROR
}

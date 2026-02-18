package ru.stepan.example.presentation.login

import androidx.compose.ui.text.input.TextFieldValue

data class LoginState(
    val login: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue()
)

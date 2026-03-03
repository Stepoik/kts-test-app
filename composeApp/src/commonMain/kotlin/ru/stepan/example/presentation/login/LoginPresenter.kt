package ru.stepan.example.presentation.login

import androidx.compose.ui.text.input.TextFieldValue

interface LoginPresenter {
    fun onLogInClicked()

    fun onChangeLogin(login: TextFieldValue)

    fun onChangePassword(password: TextFieldValue)
}
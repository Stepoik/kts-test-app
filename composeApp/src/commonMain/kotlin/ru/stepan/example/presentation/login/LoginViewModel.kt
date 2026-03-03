package ru.stepan.example.presentation.login

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), LoginPresenter {
    private val _state = MutableStateFlow(
        LoginState(
            login = savedStateHandle.getOrEmpty(LOGIN_KEY),
            password = savedStateHandle.getOrEmpty(PASSWORD_KEY)
        )
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            state.collect {
                savedStateHandle[LOGIN_KEY] = it.login.text
                savedStateHandle[PASSWORD_KEY] = it.password.text
            }
        }
    }

    override fun onLogInClicked() {
        Napier.d(message = "Logged in", tag = "LoginViewModel")
    }

    override fun onChangeLogin(login: TextFieldValue) {
        _state.update { it.copy(login = login) }
    }

    override fun onChangePassword(password: TextFieldValue) {
        _state.update { it.copy(password = password) }
    }

    private fun SavedStateHandle.getOrEmpty(key: String): TextFieldValue {
        return get<String>(key)?.let { TextFieldValue(it) } ?: TextFieldValue()
    }

    companion object {
        private const val LOGIN_KEY = "login"
        private const val PASSWORD_KEY = "password"
    }
}
package ru.stepan.reddit.auth.login

sealed class LoginScreenEvent {
    class Authorized : LoginScreenEvent()
}
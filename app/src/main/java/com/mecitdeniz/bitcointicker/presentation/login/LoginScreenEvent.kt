package com.mecitdeniz.bitcointicker.presentation.login

sealed class LoginScreenEvent {
    data class OnEmailChanged(val email: String) : LoginScreenEvent()
    data class OnPasswordChanged(val password: String) : LoginScreenEvent()
    object CreateAccount : LoginScreenEvent()
    object SignIn : LoginScreenEvent()
}
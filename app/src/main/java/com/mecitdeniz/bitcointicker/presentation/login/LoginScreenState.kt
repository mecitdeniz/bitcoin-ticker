package com.mecitdeniz.bitcointicker.presentation.login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
)
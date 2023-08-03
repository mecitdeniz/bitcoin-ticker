package com.mecitdeniz.bitcointicker.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import com.mecitdeniz.bitcointicker.domain.isValidEmail
import com.mecitdeniz.bitcointicker.domain.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authService: FirebaseAuthService
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state: State<LoginScreenState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            LoginScreenEvent.CreateAccount -> createAccount()
            LoginScreenEvent.SignIn -> singIn()
            is LoginScreenEvent.OnEmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is LoginScreenEvent.OnPasswordChanged -> {
                _state.value = state.value.copy(
                    password = event.password,
                    passwordError = null
                )
            }
        }
    }

    private fun createAccount() {
        if (!isValidForm()) return
        viewModelScope.launch {
            val result = authService.createAccount(_state.value.email, _state.value.password)

            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.LoginSuccess)
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackBar(message = result.message!!))
                }
            }
        }
    }

    private fun singIn() {
        if (!isValidForm()) return
        viewModelScope.launch {
            val result = authService.signIn(_state.value.email, _state.value.password)

            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.LoginSuccess)
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackBar(message = result.message!!))
                }
            }
        }
    }

    private fun isValidForm(): Boolean {
        val isValidEmail = _state.value.email.isValidEmail()
        val isValidPassWord = _state.value.password.isValidPassword()

        if (!isValidEmail) {
            _state.value = _state.value.copy(
                emailError = "Not a valid email!"
            )
            return false
        }

        if (!isValidPassWord) {
            _state.value = _state.value.copy(
                passwordError = "Password should be at least 6 character long"
            )
            return false
        }
        return true
    }

    sealed class UiEvent {
        object LoginSuccess : UiEvent()
        object CreateAccountSuccess : UiEvent()
        class ShowSnackBar(val message: String) : UiEvent()
    }
}


package com.mecitdeniz.bitcointicker.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    authService: FirebaseAuthService = FirebaseAuthService()
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val isLoggedIn = authService.isUserLoggedIn()
        val user = authService.getUserAccount()
        viewModelScope.launch {
            delay(200)
            _eventFlow.emit(UiEvent.LoginStatus(isLoggedIn = isLoggedIn && user != null))
        }
    }

    sealed class UiEvent {
        data class LoginStatus(val isLoggedIn: Boolean) : UiEvent()
    }
}
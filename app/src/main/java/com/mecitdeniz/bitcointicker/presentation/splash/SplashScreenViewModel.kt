package com.mecitdeniz.bitcointicker.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authService: FirebaseAuthService
) : ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    fun checkIsLoggedIn() = viewModelScope.launch {
        val isLoggedIn = authService.isUserLoggedIn()
        val user = authService.getUserAccount()
        _state.value = _state.value.copy(
            isLoggedIn = isLoggedIn && user != null
        )
    }
}


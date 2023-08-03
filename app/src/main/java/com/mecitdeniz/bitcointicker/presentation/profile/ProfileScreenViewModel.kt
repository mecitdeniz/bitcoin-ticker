package com.mecitdeniz.bitcointicker.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.data.model.User
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val authService: FirebaseAuthService = FirebaseAuthService()
) : ViewModel() {

    private val _state = mutableStateOf<User?>(null)
    val state: State<User?> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val user = authService.getUserAccount()
        if (user == null) {
            signOut()
        } else {
            _state.value = User(
                id = user.uid,
                email = user.email ?: ""
            )
        }
    }

    fun signOut() {
        authService.signOut()
        _state.value = null
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.SignOut)
        }
    }

    sealed class UiEvent {
        object SignOut : UiEvent()
    }
}
package com.mecitdeniz.bitcointicker.presentation.my_coins

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import com.mecitdeniz.bitcointicker.domain.repository.MyCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCoinsScreenViewModel @Inject constructor(
    private val myCoinsRepository: MyCoinsRepository,
    private val authService: FirebaseAuthService
): ViewModel() {

    companion object {
        private const val TAG = "MyCoinsScreenViewModel"
    }

    private val _state = mutableStateOf(MyCoinsScreenState())
    val state: State<MyCoinsScreenState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            val userId = authService.getUserAccount()?.uid ?: return@launch
            val result = myCoinsRepository.getMyCoinsFromFireStore(userId)
            when(result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        errorMessage = result.message
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        coins = result.data,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun removeFromFavorites(firebaseId: String) {
        if (firebaseId.isEmpty()) return
        viewModelScope.launch {
            val result = myCoinsRepository.deleteCoinFromFireStore(firebaseId)
            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "Document removed.. ${result.data}")
                    getCoins()
                }
                is Resource.Error -> {
                    result.message?.let {
                        Log.e(TAG, it)
                    }
                    getCoins()
                }
            }
        }
    }
}
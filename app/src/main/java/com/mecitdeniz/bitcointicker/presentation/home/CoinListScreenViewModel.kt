package com.mecitdeniz.bitcointicker.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.domain.use_case.GetCoinMarketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinListScreenViewModel @Inject constructor(
    private val getCoinMarketsUseCase: GetCoinMarketsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListScreenState())
    val state: State<CoinListScreenState> = _state

    init {
        getCoinMarkets()
    }

    private fun getCoinMarkets() {
        _state.value = _state.value.copy(
            isLoading = true
        )

        getCoinMarketsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        coins = result.data,
                        errorMessage = null
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.mecitdeniz.bitcointicker.presentation.coin_detail

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mecitdeniz.bitcointicker.common.Constants
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.common.Utils
import com.mecitdeniz.bitcointicker.domain.use_case.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailScreenViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailScreenState())
    val state: State<CoinDetailScreenState> = _state

    private var refreshDataJob: Job? = null

    init {
        getIntervalValue()
        getCoinId()
        startRefreshDataJob()
    }

    private var count = 0

    private fun getCoinId() {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            _state.value = _state.value.copy(
                coinId = coinId
            )
        }
    }

    private fun getIntervalValue() {
        val interval = sharedPreferences.getLong(
            Constants.INTERVAL_PREF,
            Constants.REFRESH_INTERVAL.first().second
        )
        println("Pref: $interval")
        _state.value = _state.value.copy(
            refreshInterval = interval
        )
    }

    fun setIntervalValue(value: String) {
        println("setIntervalValue $value")
        val interval = Utils.getIntervalLong(value)
        if (interval == _state.value.refreshInterval) {
            return
        }
        _state.value = _state.value.copy(
            refreshInterval = interval
        )

        val editor = sharedPreferences.edit()
        editor.putLong(
            Constants.INTERVAL_PREF,
            interval
        )
        editor.apply()

        resetRefreshDataJob()
    }

    private fun resetRefreshDataJob() {
        println("resetRefreshDataJob")
        refreshDataJob?.cancel()
        refreshDataJob = null
        startRefreshDataJob()
    }

    private fun startRefreshDataJob() {
        val coinId = _state.value.coinId
        val refreshInterval = _state.value.refreshInterval
        if (refreshInterval == null || coinId == null) return
        if (refreshDataJob == null || !refreshDataJob!!.isActive) {
            refreshDataJob = viewModelScope.launch {
                while (isActive) {
                    Log.d("Job", "fetch data $count")
                    getCoin(coinId)
                    count++
                    delay(refreshInterval)
                }
            }
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = null,
                        coin = result.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
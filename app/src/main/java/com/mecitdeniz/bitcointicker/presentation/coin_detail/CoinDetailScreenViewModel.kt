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
import com.mecitdeniz.bitcointicker.domain.repository.MyCoinsRepository
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
    private val myCoinsRepository: MyCoinsRepository,
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val TAG = "CoinDetailScreenViewModel"
    }

    private val _state = mutableStateOf(CoinDetailScreenState())
    val state: State<CoinDetailScreenState> = _state

    private var refreshDataJob: Job? = null

    init {
        getIntervalValue()
        getCoinId()
        startRefreshDataJob()
    }

    private fun getCoinId() {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            _state.value = _state.value.copy(
                coinId = coinId
            )
            isInFavorites(coinId)
        }
    }

    private fun getIntervalValue() {
        val interval = sharedPreferences.getLong(
            Constants.INTERVAL_PREF,
            Constants.REFRESH_INTERVAL.first().second
        )
        _state.value = _state.value.copy(
            refreshInterval = interval
        )
    }

    fun setIntervalValue(value: String) {
        Log.d(TAG, "setIntervalValue: $value")
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
        Log.d(TAG, "resetRefreshDataJob")
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
                    Log.d(TAG, "fetch data")
                    getCoin(coinId)
                    delay(refreshInterval)
                }
            }
        }
    }

    private fun isInFavorites(coinId: String) {
        viewModelScope.launch {
            val result = myCoinsRepository.getCoinById(coinId)
            when (result) {
                is Resource.Error -> Unit
                is Resource.Success -> {
                    val firebaseId = result.data.toString()
                    _state.value = _state.value.copy(
                        isInFavorites = firebaseId.isNotEmpty(),
                        firebaseId = firebaseId
                    )
                }
            }

        }
    }

    fun onFavoritesButtonClick() {
        val coin = _state.value.coin ?: return
        viewModelScope.launch {
            val result = myCoinsRepository.getCoinById(coin.id)
            when (result) {
                is Resource.Error -> Unit
                is Resource.Success -> {
                    val isExist = result.data.toString().isNotEmpty()
                    if (!isExist) {
                        addToFavorites()
                        return@launch
                    }
                    removeFromFavorites()
                }
            }

        }
    }

    private fun removeFromFavorites() {
        val firebaseId = _state.value.firebaseId
        if (firebaseId.isEmpty()) return
        viewModelScope.launch {
            val result = myCoinsRepository.deleteCoinFromFireStore(firebaseId)
            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "Document removed.. ${result.data}")
                    _state.value = _state.value.copy(
                        isInFavorites = false,
                        firebaseId = ""
                    )
                }
                is Resource.Error -> {
                    result.message?.let {
                        Log.e(TAG, it)
                    }
                }
            }
        }
    }

    private fun addToFavorites() {
        val coin = _state.value.coin ?: return
        viewModelScope.launch {
            val result = myCoinsRepository.addCoinToFireStore(coin)
            when (result) {
                is Resource.Success -> {
                    Log.d(TAG, "Document added.. ${result.data}")
                    _state.value = _state.value.copy(
                        isInFavorites = true
                    )
                    isInFavorites(coin.id)
                }
                is Resource.Error -> {
                    result.message?.let {
                        Log.e(TAG, it)
                    }
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
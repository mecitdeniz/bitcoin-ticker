package com.mecitdeniz.bitcointicker.presentation.coin_detail

import com.mecitdeniz.bitcointicker.domain.model.CoinDetail

data class CoinDetailScreenState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val coinId: String? = null,
    val refreshInterval: Long? = null,
    val errorMessage: String? = null,
    val isInFavorites: Boolean = false,
    val firebaseId: String = ""
)

package com.mecitdeniz.bitcointicker.presentation.coin_detail

import com.mecitdeniz.bitcointicker.data.remote.dto.CoinDetailDto

data class CoinDetailScreenState(
    val isLoading: Boolean = false,
    val coin: CoinDetailDto? = null,
    val errorMessage: String? = null,
)

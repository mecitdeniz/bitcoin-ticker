package com.mecitdeniz.bitcointicker.presentation.coin_list

import com.mecitdeniz.bitcointicker.domain.model.Coin

data class CoinListScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin>? = emptyList(),
    val errorMessage: String? = null
)

package com.mecitdeniz.bitcointicker.presentation.home

import com.mecitdeniz.bitcointicker.domain.model.Coin

data class CoinListScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin>? = emptyList(),
    val errorMessage: String? = null
)

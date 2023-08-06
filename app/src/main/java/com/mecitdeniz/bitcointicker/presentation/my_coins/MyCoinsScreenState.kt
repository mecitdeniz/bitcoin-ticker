package com.mecitdeniz.bitcointicker.presentation.my_coins

import com.mecitdeniz.bitcointicker.domain.model.CoinDetail

data class MyCoinsScreenState(
    val coins: List<CoinDetail>? = emptyList(),
    val errorMessage: String? = null
)

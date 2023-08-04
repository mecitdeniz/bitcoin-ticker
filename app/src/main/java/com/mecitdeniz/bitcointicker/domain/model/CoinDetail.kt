package com.mecitdeniz.bitcointicker.domain.model

data class CoinDetail(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double?,
    val description: String?,
    val hashingAlgorithm: String?,
    val marketCapRank: Double,
    val isInFavorites: Boolean = false,
    val priceChangePercentage24H: Double,
)

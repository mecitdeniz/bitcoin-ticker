package com.mecitdeniz.bitcointicker.domain.model

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: Double,
    val priceChangePercentage24H: Double,
    val firebaseId: String,
)
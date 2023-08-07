package com.mecitdeniz.bitcointicker.domain.model

data class CoinDetail(
    val id: String = "",
    val symbol: String = "",
    val name: String  = "",
    val image: String  = "",
    val currentPrice: Double? = 0.0,
    val description: String? = "",
    val hashingAlgorithm: String? = "",
    val marketCapRank: Double = 0.0,
    val priceChangePercentage24H: Double = 0.0,
    var firebaseId: String? = "",
    var firebaseUserId: String? = ""
)

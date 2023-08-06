package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.mecitdeniz.bitcointicker.domain.model.Coin

data class CoinMarketDto (
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,

    @SerializedName("current_price")
    val currentPrice: Double,

    @SerializedName("market_cap")
    val marketCap: Double,

    @SerializedName("market_cap_rank")
    val marketCapRank: Double,

    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Double,

    @SerializedName("total_volume")
    val totalVolume: Double,

    @SerializedName("high_24h")
    val high24H: Double,

    @SerializedName("low_24h")
    val low24H: Double,

    @SerializedName("price_change_24h")
    val priceChange24H: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24H: Double,

    @SerializedName("market_cap_change_24h")
    val marketCapChange24H: Double,

    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24H: Double,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double,

    @SerializedName("total_supply")
    val totalSupply: Double,

    @SerializedName("max_supply")
    val maxSupply: Double,

    val ath: Double,

    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double,

    @SerializedName("ath_date")
    val athDate: String,

    val atl: Double,

    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double,

    @SerializedName("atl_date")
    val atlDate: String,

    val roi: JsonElement? = null,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24HInCurrency: Double
)


fun CoinMarketDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        image = image,
        currentPrice = currentPrice,
        marketCapRank = marketCapRank,
        priceChangePercentage24H =  priceChangePercentage24H,
        firebaseId = ""
    )
}

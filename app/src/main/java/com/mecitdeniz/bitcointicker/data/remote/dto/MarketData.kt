package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class MarketData (
    @SerializedName("current_price")
    val currentPrice: Map<String, Double>,

    @SerializedName("total_value_locked")
    val totalValueLocked: JsonElement? = null,

    @SerializedName("mcap_to_tvl_ratio")
    val mcapToTvlRatio: JsonElement? = null,

    @SerializedName("fdv_to_tvl_ratio")
    val fdvToTvlRatio: JsonElement? = null,

    val roi: JsonElement? = null,
    val ath: Map<String, Double>,

    @SerializedName("ath_change_percentage")
    val athChangePercentage: Map<String, Double>,

    @SerializedName("ath_date")
    val athDate: Map<String, String>,

    val atl: Map<String, Double>,

    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Map<String, Double>,

    @SerializedName("atl_date")
    val atlDate: Map<String, String>,

    @SerializedName("market_cap")
    val marketCap: Map<String, Double>,

    @SerializedName("market_cap_rank")
    val marketCapRank: Double,

    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Map<String, Double>,

    @SerializedName("total_volume")
    val totalVolume: Map<String, Double>,

    @SerializedName("high_24h")
    val high24H: Map<String, Double>,

    @SerializedName("low_24h")
    val low24H: Map<String, Double>,

    @SerializedName("price_change_24h")
    val priceChange24H: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24H: Double,

    @SerializedName("price_change_percentage_7d")
    val priceChangePercentage7D: Double,

    @SerializedName("price_change_percentage_14d")
    val priceChangePercentage14D: Double,

    @SerializedName("price_change_percentage_30d")
    val priceChangePercentage30D: Double,

    @SerializedName("price_change_percentage_60d")
    val priceChangePercentage60D: Double,

    @SerializedName("price_change_percentage_200d")
    val priceChangePercentage200D: Double,

    @SerializedName("price_change_percentage_1y")
    val priceChangePercentage1Y: Double,

    @SerializedName("market_cap_change_24h")
    val marketCapChange24H: Double,

    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24H: Double,

    @SerializedName("price_change_24h_in_currency")
    val priceChange24HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_14d_in_currency")
    val priceChangePercentage14DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_30d_in_currency")
    val priceChangePercentage30DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_60d_in_currency")
    val priceChangePercentage60DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_200d_in_currency")
    val priceChangePercentage200DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_1y_in_currency")
    val priceChangePercentage1YInCurrency: Map<String, Double>,

    @SerializedName("market_cap_change_24h_in_currency")
    val marketCapChange24HInCurrency: Map<String, Double>,

    @SerializedName("market_cap_change_percentage_24h_in_currency")
    val marketCapChangePercentage24HInCurrency: Map<String, Double>,

    @SerializedName("total_supply")
    val totalSupply: Double,

    @SerializedName("max_supply")
    val maxSupply: Double,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double,

    @SerializedName("last_updated")
    val lastUpdated: String
)

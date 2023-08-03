package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class CoinDetailDto (
    val id: String,
    val symbol: String,
    val name: String,

    @SerializedName("asset_platform_id")
    val assetPlatformID: JsonElement? = null,

    val platforms: Platforms,

    @SerializedName("detail_platforms")
    val detailPlatforms: DetailPlatforms,

    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Long,

    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,

    val categories: List<String>,

    @SerializedName("public_notice")
    val publicNotice: JsonElement? = null,

    @SerializedName("additional_notices")
    val additionalNotices: List<JsonElement?>?,

    val description: Description,
    val links: Links,
    val image: Image,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("genesis_date")
    val genesisDate: String,

    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double,

    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double,

    @SerializedName("watchlist_portfolio_users")
    val watchlistPortfolioUsers: Long,

    @SerializedName("market_cap_rank")
    val marketCapRank: Long,

    @SerializedName("coingecko_rank")
    val coingeckoRank: Long,

    @SerializedName("coingecko_score")
    val coingeckoScore: Double,

    @SerializedName("developer_score")
    val developerScore: Double,

    @SerializedName("community_score")
    val communityScore: Double,

    @SerializedName("liquidity_score")
    val liquidityScore: Double,

    @SerializedName("public_interest_score")
    val publicInterestScore: Double,

    @SerializedName("market_data")
    val marketData: MarketData,

    @SerializedName("public_interest_stats")
    val publicInterestStats: PublicInterestStats,

    @SerializedName("status_updates")
    val statusUpdates: List<JsonElement?>?,

    @SerializedName("last_updated")
    val lastUpdated: String
)








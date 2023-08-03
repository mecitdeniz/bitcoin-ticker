package com.mecitdeniz.bitcointicker.data.remote

import com.mecitdeniz.bitcointicker.data.remote.dto.CoinDetailDto
import com.mecitdeniz.bitcointicker.data.remote.dto.CoinMarketDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinGeckoApi {

    @GET("v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&sparkline=false&price_change_percentage=24h&locale=en")
    suspend fun getCoinMarkets(): List<CoinMarketDto>

    @GET("v3/coins/{coinId}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}
package com.mecitdeniz.bitcointicker.domain.repository

import com.mecitdeniz.bitcointicker.data.remote.dto.CoinDetailDto
import com.mecitdeniz.bitcointicker.data.remote.dto.CoinMarketDto

interface CoinRepository {

    suspend fun getCoinMarkets(): List<CoinMarketDto>

    suspend fun getCoinBuId(coinId: String): CoinDetailDto
}
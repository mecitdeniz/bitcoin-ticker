package com.mecitdeniz.bitcointicker.data.repository

import com.mecitdeniz.bitcointicker.data.remote.CoinGeckoApi
import com.mecitdeniz.bitcointicker.data.remote.dto.CoinDetailDto
import com.mecitdeniz.bitcointicker.data.remote.dto.CoinMarketDto
import com.mecitdeniz.bitcointicker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
): CoinRepository {

    override suspend fun getCoinMarkets(): List<CoinMarketDto> {
        return api.getCoinMarkets()
    }

    override suspend fun getCoinBuId(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}
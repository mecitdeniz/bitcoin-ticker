package com.mecitdeniz.bitcointicker.domain.repository

import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.domain.model.CoinDetail

interface MyCoinsRepository {

    suspend fun getMyCoinsFromFireStore(): Resource<List<CoinDetail>>

    suspend fun getCoinById(coinId: String): Resource<String>

    suspend fun addCoinToFireStore(coin: CoinDetail): Resource<Boolean>

    suspend fun deleteCoinFromFireStore(firebaseId: String): Resource<Boolean>
}
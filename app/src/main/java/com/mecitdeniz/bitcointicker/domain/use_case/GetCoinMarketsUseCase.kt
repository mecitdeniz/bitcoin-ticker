package com.mecitdeniz.bitcointicker.domain.use_case

import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.data.remote.dto.toCoin
import com.mecitdeniz.bitcointicker.domain.model.Coin
import com.mecitdeniz.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinMarketsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            val coiMarkets = repository.getCoinMarkets().map { it.toCoin() }
            emit(Resource.Success(data = coiMarkets))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}
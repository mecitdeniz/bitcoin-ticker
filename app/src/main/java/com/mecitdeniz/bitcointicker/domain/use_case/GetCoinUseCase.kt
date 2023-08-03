package com.mecitdeniz.bitcointicker.domain.use_case

import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.data.remote.dto.CoinDetailDto
import com.mecitdeniz.bitcointicker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetailDto>> = flow {
        try {
            val coin = repository.getCoinBuId(coinId)
            emit(Resource.Success(data = coin))
        } catch (e: Exception) {
            println(e.printStackTrace())
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}
package com.mecitdeniz.bitcointicker.data.repository

import com.google.firebase.firestore.CollectionReference
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.domain.model.CoinDetail
import com.mecitdeniz.bitcointicker.domain.repository.MyCoinsRepository
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class MyCoinsRepositoryImpl @Inject constructor(
    private val myCoinsRef: CollectionReference
) : MyCoinsRepository {
    override suspend fun getMyCoinsFromFireStore(): Resource<List<CoinDetail>> {
        val def = CompletableDeferred<Resource<List<CoinDetail>>>()
        myCoinsRef
            .orderBy("marketCapRank")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val data = it.result.toObjects(CoinDetail::class.java)
                    val ids = mutableListOf<String>()
                    it.result.documents.forEach { document ->
                        ids.add(document.id)
                    }
                    data.forEachIndexed { index, coinDetail ->
                        coinDetail.firebaseId = ids[index]
                    }
                    def.complete(Resource.Success(data = data))
                } else {
                    def.complete(
                        Resource.Error(
                            message = it.exception?.message ?: "Unable to retrieve data"
                        )
                    )
                }
            }
        return def.await()
    }

    override suspend fun getCoinById(coinId: String): Resource<String> {
        val def = CompletableDeferred<Resource<String>>()
        myCoinsRef
            .whereEqualTo("id", coinId)
            .get()
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    def.complete(
                        Resource.Success(it.documents.first().id)
                    )
                } else {
                    def.complete(
                        Resource.Success("")
                    )
                }
            }
            .addOnFailureListener {
                def.complete(
                    Resource.Error(
                        message = it.message ?: "Unable to save coin to favorites"
                    )
                )
            }
        return def.await()
    }

    override suspend fun addCoinToFireStore(coin: CoinDetail): Resource<Boolean> {
        val def = CompletableDeferred<Resource<Boolean>>()

        myCoinsRef
            .add(coin)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    def.complete(Resource.Success(data = it.isSuccessful))
                } else {
                    def.complete(
                        Resource.Error(
                            message = it.exception?.message ?: "Unable to save coin to favorites"
                        )
                    )
                }
            }
        return def.await()
    }

    override suspend fun deleteCoinFromFireStore(firebaseId: String): Resource<Boolean> {
        val def = CompletableDeferred<Resource<Boolean>>()
        myCoinsRef
            .document(firebaseId)
            .delete()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    def.complete(Resource.Success(data = it.isSuccessful))
                } else {
                    def.complete(
                        Resource.Error(
                            message = it.exception?.message
                                ?: "Unable to delete coin from favorites"
                        )
                    )
                }
            }
        return def.await()
    }
}
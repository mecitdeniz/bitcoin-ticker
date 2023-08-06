package com.mecitdeniz.bitcointicker.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mecitdeniz.bitcointicker.common.Constants
import com.mecitdeniz.bitcointicker.data.remote.CoinGeckoApi
import com.mecitdeniz.bitcointicker.data.repository.CoinRepositoryImpl
import com.mecitdeniz.bitcointicker.data.repository.MyCoinsRepositoryImpl
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import com.mecitdeniz.bitcointicker.domain.repository.CoinRepository
import com.mecitdeniz.bitcointicker.domain.repository.MyCoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthService(): FirebaseAuthService {
        return FirebaseAuthService()
    }

    @Provides
    @Singleton
    fun provideMyCoinsRef() = Firebase.firestore.collection(Constants.MY_COINS_REF)

    @Provides
    @Singleton
    fun provideMyCoinsRepository(myCoinsRef: CollectionReference): MyCoinsRepository =
        MyCoinsRepositoryImpl(myCoinsRef)

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinGeckoApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context
            .getSharedPreferences(
                Constants.SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
    }
}
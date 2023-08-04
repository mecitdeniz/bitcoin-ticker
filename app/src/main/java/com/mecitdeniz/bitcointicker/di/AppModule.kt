package com.mecitdeniz.bitcointicker.di

import android.content.Context
import android.content.SharedPreferences
import com.mecitdeniz.bitcointicker.common.Constants
import com.mecitdeniz.bitcointicker.data.remote.CoinGeckoApi
import com.mecitdeniz.bitcointicker.data.repository.CoinRepositoryImpl
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import com.mecitdeniz.bitcointicker.domain.repository.CoinRepository
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
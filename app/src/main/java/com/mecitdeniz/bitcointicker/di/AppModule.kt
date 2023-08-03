package com.mecitdeniz.bitcointicker.di

import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthService(): FirebaseAuthService {
        return FirebaseAuthService()
    }
}
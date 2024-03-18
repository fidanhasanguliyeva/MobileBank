package com.mobilebank.di

import com.mobilebank.domain.remote.CardServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule
{
    @Singleton
    @Provides
    fun providesCardServices(retrofit: Retrofit): CardServices {
        return retrofit.create(CardServices::class.java)
    }

}
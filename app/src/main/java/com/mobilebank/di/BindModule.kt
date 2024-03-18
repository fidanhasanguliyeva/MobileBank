package com.mobilebank.di

import com.mobilebank.data.repository.CardRepositoryImpl
import com.mobilebank.domain.repository.CardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Singleton
    @Binds
    abstract fun bindCardRepository(repo: CardRepositoryImpl): CardRepository

}
package com.mobilebank.data.repository

import android.content.Context
import com.mobile.onsual.domain.model.ResultWrapper
import com.mobilebank.data.model.response.CardResponse
import com.mobilebank.data.model.response.TransactionsResponse
import com.mobilebank.domain.core.BaseRepository
import com.mobilebank.domain.remote.CardServices
import com.mobilebank.domain.repository.CardRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    val services: CardServices
) : BaseRepository(context), CardRepository {

    override suspend fun getCards(): ResultWrapper<Response<CardResponse>> {
        return handleNetwork {
            services.getCards()
        }
    }

    override suspend fun getTransactions(cardNumber: String): ResultWrapper<Response<TransactionsResponse>> {
        return handleNetwork {
            services.getTransactions(cardNumber)
        }
    }
}
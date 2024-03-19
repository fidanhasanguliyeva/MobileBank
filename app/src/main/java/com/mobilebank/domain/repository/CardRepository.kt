package com.mobilebank.domain.repository

import com.mobile.onsual.domain.model.ResultWrapper
import com.mobilebank.data.model.response.CardResponse
import com.mobilebank.data.model.response.TransactionsResponse
import retrofit2.Response

interface CardRepository {

    suspend fun getCards(): ResultWrapper<Response<CardResponse>>
    suspend fun getTransactions(cardNumber:String): ResultWrapper<Response<TransactionsResponse>>

}
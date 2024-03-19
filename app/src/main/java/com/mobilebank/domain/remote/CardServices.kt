package com.mobilebank.domain.remote

import com.mobilebank.data.model.response.CardResponse
import com.mobilebank.data.model.response.TransactionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CardServices {

    @GET("/cards")
    suspend fun getCards(): Response<CardResponse>

    @GET("/getTransactions/{cardNumber}")
    suspend fun getTransactions(@Path("cardNumber") cardNumber: String): Response<TransactionsResponse>

}
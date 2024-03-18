package com.mobilebank.domain.remote

import com.mobilebank.data.model.response.CardResponse
import retrofit2.Response
import retrofit2.http.GET

interface CardServices {

    @GET("/cards")
    suspend fun getCards(): Response<CardResponse>

}
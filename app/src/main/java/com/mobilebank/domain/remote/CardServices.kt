package com.mobilebank.domain.remote

import retrofit2.http.GET

interface CardServices {

    @GET("/cards")
    suspend fun getCards():

}
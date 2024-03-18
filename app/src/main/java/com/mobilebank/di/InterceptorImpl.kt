package com.mobilebank.di

import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class InterceptorImpl : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader(
                "X-RapidAPI-Key",
                "599cfc1d32msh26e39f8002adb7dp1c367fjsn24eb7acd8d9e"
            )
            .addHeader("X-RapidAPI-Host", "mobile-bank.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}

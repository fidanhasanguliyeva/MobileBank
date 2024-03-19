package com.mobilebank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor {
                val request: Request = it.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader(
                        "X-RapidAPI-Key",
                        "599cfc1d32msh26e39f8002adb7dp1c367fjsn24eb7acd8d9e"
                    )
                    .addHeader("X-RapidAPI-Host", "mobile-bank.p.rapidapi.com")
                    .build()
                it.proceed(request)
            })
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://mobile-bank.p.rapidapi.com")
        .client(okHttpClient)
        .build()


}

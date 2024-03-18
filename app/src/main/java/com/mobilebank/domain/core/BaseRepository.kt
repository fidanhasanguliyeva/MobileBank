package com.mobilebank.domain.core

import android.content.Context
import com.google.gson.Gson
import com.mobile.onsual.domain.model.ErrorResponse
import com.mobile.onsual.domain.model.ResultWrapper
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

open class BaseRepository(
   val context: Context
) {

    companion object {
        const val GENERAL_ERROR_MESSAGE = "Texniki xəta baş verdi. Xahiş edirik bir daha cəhd edin."
    }



//    @InstallIn(SingletonComponent::class)
//    @EntryPoint
//    interface BaseRepositoryEntryPoint {
//        fun tokenManager(): TokenInterceptor
//        fun authServices(): CustomerServices
//    }

    init {
//        val entryPoint =
//            EntryPointAccessors.fromApplication(context, BaseRepositoryEntryPoint::class.java)
//        customerServices = entryPoint.authServices()
//        tokenManager = entryPoint.tokenManager()
    }

    suspend fun <T> handleNetwork(
        apiCall: suspend () -> Response<T>?
    ): ResultWrapper<Response<T>> {
        return try {
            val result = apiCall()
            if (result?.isSuccessful == true) {
                ResultWrapper.Success(result)
            } else {
                val errorBody = Gson().fromJson(
                    result?.errorBody()?.string(), ErrorResponse::class.java
                ).let {
                    if (it.status == null && result?.code() == 401) {
                        it.copy(status = 401)
                    } else {
                        it
                    }
                }
                ResultWrapper.GenericError(errorBody)
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is SocketTimeoutException -> ResultWrapper.GenericError(
                    ErrorResponse(
                        message = GENERAL_ERROR_MESSAGE,
                        status = 522
                    )
                )
//                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val errorResponse = ErrorResponse(message = GENERAL_ERROR_MESSAGE)
                    ResultWrapper.GenericError(errorResponse)
                }

                else -> {
                    ResultWrapper.GenericError(ErrorResponse())
                }
            }
        }
    }


//    suspend inline fun <reified T> handleNetwork(
//        crossinline apiCall: suspend () -> Response<T>
//    ): ResultWrapper<Response<T>> {
//
//        val wrapper = apiCallInternal {
//            apiCall()
//        }
//        return if (wrapper is ResultWrapper.GenericError &&
//            wrapper.error?.status == 401
//        ) {
////            tokenManager.setAccessToken(null)
//            handleRefreshTokenResult {
//                apiCall()
//            }
//        } else {
//            return wrapper
//        }
//
//    }



}
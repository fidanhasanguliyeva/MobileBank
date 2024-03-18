package com.mobile.onsual.domain.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T, val isCachedValue: Boolean = false) : ResultWrapper<T>()
    data class GenericError(val error: ErrorResponse? = null) : ResultWrapper<Nothing>()
//    object NetworkError : ResultWrapper<Nothing>()
}
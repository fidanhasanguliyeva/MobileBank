package com.mobile.onsual.domain.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("detail")
    val detail: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("requested_lang")
    val requestedLang: String? = null,
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("timestamp")
    val timestamp: String? = null
) {
//    fun toFail(): Fail {
//        return Fail(message, detail)
//    }
}

data class Error(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("property")
    var `property`: String? = null
)
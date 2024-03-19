package com.mobilebank.data.model.response


import com.google.gson.annotations.SerializedName

data class TransactionsResponse(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("income")
    val income: Int?,
    @SerializedName("outgoing")
    val outgoing: Double?,
    @SerializedName("transactions")
    val transactions: List<Transaction?>?
)
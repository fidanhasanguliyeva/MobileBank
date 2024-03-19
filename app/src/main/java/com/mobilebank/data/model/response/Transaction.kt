package com.mobilebank.data.model.response


import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("commissionFee")
    val commissionFee: Any?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_extra")
    val descriptionExtra: String?,
    @SerializedName("localAmount")
    val localAmount: Int?,
    @SerializedName("localCurrency")
    val localCurrency: Any?,
    @SerializedName("paymentSource")
    val paymentSource: String?,
    @SerializedName("realAmount")
    val realAmount: Any?,
    @SerializedName("realCurrency")
    val realCurrency: Any?,
    @SerializedName("rrn")
    val rrn: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("taxAmount")
    val taxAmount: Any?,
    @SerializedName("taxCurrency")
    val taxCurrency: Any?,
    @SerializedName("transactionAmount")
    val transactionAmount: Any?
)
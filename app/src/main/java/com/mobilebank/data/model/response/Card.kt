package com.mobilebank.data.model.response


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("alias")
    val alias: String? = null,
    @SerializedName("availableBalance")
    val availableBalance: String? = null,
    @SerializedName("cardHolderName")
    val cardHolderName: String? = null,
    @SerializedName("cardNetwork")
    val cardNetwork: String? = null,
    @SerializedName("cardNumber")
    val cardNumber: String? = null,
    @SerializedName("cardStatus")
    val cardStatus: String? = null,
    @SerializedName("cif")
    val cif: String? = null,
    @SerializedName("contractNumber")
    val contractNumber: String? = null,
    @SerializedName("creditLimit")
    val creditLimit: Int? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("expiryDate")
    val expiryDate: String? = null,
    @SerializedName("isOtherBankCard")
    val isOtherBankCard: Boolean? = null,
    @SerializedName("isPinEraseEligible")
    val isPinEraseEligible: Boolean? = null,
    @SerializedName("maskedCardNumber")
    val maskedCardNumber: String? = null,
    @SerializedName("mediumImageUrl")
    val mediumImageUrl: String? = null,
    @SerializedName("offlineBalance")
    val offlineBalance: Boolean? = null,
    @SerializedName("productType")
    val productType: String? = null,
    @SerializedName("updateDate")
    val updateDate: String? = null
)
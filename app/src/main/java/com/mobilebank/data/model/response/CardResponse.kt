package com.mobilebank.data.model.response


import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("cards")
    val cards: List<Card?>? = null
)
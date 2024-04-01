package com.mobilebank.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val cardNumber: String,
    val amount: String,
    val backgroundImage: String,
    val expiryDate: String,
    val name: String
):Parcelable

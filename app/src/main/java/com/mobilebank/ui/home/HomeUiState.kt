package com.mobilebank.ui.home

import com.mobilebank.data.model.CardUiModel
import com.mobilebank.data.model.response.CardResponse
import com.onsual.base.UiState

data class HomeUiState(
    val isLoading: Boolean = false, val listOfCards: List<CardUiModel> = arrayListOf()
) : UiState {

    fun mapCardResponseToUiModel(response: CardResponse?): List<CardUiModel> {
        return response?.cards?.map {
            CardUiModel(
                cardNumber = it?.maskedCardNumber ?: "",
                amount = (it?.availableBalance ?: "") + " " + it?.currency,
                backgroundImage = it?.mediumImageUrl ?: ""
            )
        } ?: listOf()
    }
}

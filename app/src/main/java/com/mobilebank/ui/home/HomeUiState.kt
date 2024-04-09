package com.mobilebank.ui.home

import com.mobilebank.R
import com.mobilebank.data.model.CardUiModel
import com.mobilebank.data.model.TransactionsUiModel
import com.mobilebank.data.model.response.CardResponse
import com.mobilebank.data.model.response.TransactionsResponse
import com.onsual.base.UiState

data class HomeUiState(
    val isLoading: Boolean = false,
    val listOfCards: List<CardUiModel> = arrayListOf(),
    val listOfTransactions: List<TransactionsUiModel> = arrayListOf()
) : UiState {

    fun mapCardResponseToUiModel(response: CardResponse?): List<CardUiModel> {
        return response?.cards?.map {
            CardUiModel(
                cardNumber = it?.maskedCardNumber ?: "",
                amount = (it?.availableBalance ?: "") + " " + it?.currency,
                backgroundImage = it?.mediumImageUrl ?: "",
                expiryDate = it?.expiryDate ?: "",
                name = it?.alias ?: ""
            )
        } ?: listOf()
    }

    fun mapTransactionResponseToUiModel(response: TransactionsResponse?): List<TransactionsUiModel> {
        return response?.transactions?.map {
            TransactionsUiModel(
                title = it?.description ?: "",
                amount = it?.amount ?: 0.0,
                subtitle = it?.date ?: "",
                endLabel = (it?.amount ?: 0.0).toString(),
                icon = if ((it?.amount ?: 0.0) > 0
                ) R.drawable.ic_transaction_spending else R.drawable.ic_transaction_income
            )
        } ?: listOf()
    }
}

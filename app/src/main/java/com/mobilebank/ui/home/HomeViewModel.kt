package com.mobilebank.ui.home

import androidx.lifecycle.viewModelScope
import com.mobile.onsual.domain.model.ResultWrapper
import com.mobilebank.domain.repository.CardRepository
import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val cardRepository: CardRepository) :
    BaseViewModel<HomeUiState>() {
    override fun createInitialState(): HomeUiState {
        return HomeUiState()
    }

    fun getCards() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { copy(isLoading = true) }
            when (val response = cardRepository.getCards()) {
                is ResultWrapper.Success -> {
                    val data = response.value.body()
                    setState {
                        copy(
                            isLoading = false,
                            listOfCards = mapCardResponseToUiModel(data)
                        )
                    }
                }

                is ResultWrapper.GenericError -> {
                    setState { copy(isLoading = false) }
                }
            }
        }
    }
}
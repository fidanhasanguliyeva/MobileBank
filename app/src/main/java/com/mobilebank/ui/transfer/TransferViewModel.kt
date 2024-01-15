package com.mobilebank.ui.transfer

import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor() : BaseViewModel<TransferUiState>() {

    override fun createInitialState(): TransferUiState {
        return TransferUiState()
    }
}
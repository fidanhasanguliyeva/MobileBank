package com.mobilebank.ui.home

import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUiState>() {
    override fun createInitialState(): HomeUiState {
        return HomeUiState()
    }

}
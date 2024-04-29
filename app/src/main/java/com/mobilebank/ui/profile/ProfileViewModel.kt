package com.mobilebank.ui.profile

import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel<ProfileUiState>() {
    override fun createInitialState(): ProfileUiState {
        return ProfileUiState()
    }


    fun createUiList() {
        currentState.listOfViews.addAll(currentState.returnMenuModels())
        setState {
            copy(listOfViews = currentState.listOfViews)
        }
    }
}
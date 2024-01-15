package com.mobilebank.ui.login

import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel<LoginUiState>() {

    override fun createInitialState(): LoginUiState {
        return LoginUiState()
    }

}
package com.mobilebank.ui.signup

import com.onsual.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SignupViewModel @Inject constructor(): BaseViewModel<SignupUiState>() {
    override fun createInitialState(): SignupUiState {
        return SignupUiState()
    }
}
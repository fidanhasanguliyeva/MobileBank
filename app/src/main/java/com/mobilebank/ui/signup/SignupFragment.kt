package com.mobilebank.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mobilebank.databinding.FragmentSignupBinding
import com.mobilebank.ui.base.BaseFragment

class SignupFragment :
    BaseFragment<FragmentSignupBinding, SignupUiState, SignupViewModel>(SignupViewModel::class) {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate


}
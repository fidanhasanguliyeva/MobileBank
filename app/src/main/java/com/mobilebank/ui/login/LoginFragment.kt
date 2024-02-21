package com.mobilebank.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobilebank.databinding.FragmentLoginBinding
import com.mobilebank.ui.base.BaseFragment


class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginUiState, LoginViewModel>(LoginViewModel::class) {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }

}
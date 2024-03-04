package com.mobilebank.ui.login

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.mobilebank.R
import com.mobilebank.databinding.FragmentLoginBinding
import com.mobilebank.ui.base.BaseFragment


class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginUiState, LoginViewModel>(LoginViewModel::class) {

    var textAppearance: Int = R.style.InputTextLabel16
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            magnifier.zoomInBtn.setOnClickListener {
                Log.i(
                    "fidan",
                    "onViewCreated: ${editTextPhone.placeholderTextAppearance}  ${R.style.InputTextLabel14}"
                )
                increaseTextSize(textAppearance)
            }

            magnifier.zoomOutBtn.setOnClickListener {

            }
        }
    }

    fun increaseTextSize(style: Int) {
        textAppearance = style
        if (textAppearance == R.style.InputTextLabel16) {
            textAppearance = R.style.InputTextLabel18
            binding.editTextPhone.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
            binding.editTextPhone.layoutParams.height = 70.asDp
        } else if (textAppearance == R.style.InputTextLabel18) {
            textAppearance = R.style.InputTextLabel20
            binding.editTextPhone.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f)
        }
        binding.editTextPhone.setHintTextAppearance(textAppearance)
    }
}
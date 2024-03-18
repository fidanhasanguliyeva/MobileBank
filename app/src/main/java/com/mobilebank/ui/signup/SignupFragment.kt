package com.mobilebank.ui.signup

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilebank.R
import com.mobilebank.databinding.FragmentSignupBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.asDp
import java.util.Locale

class SignupFragment :
    BaseFragment<FragmentSignupBinding, SignupUiState, SignupViewModel>(SignupViewModel::class) {

    var textAppearance: Int = R.style.InputTextLabel16
    var textToSpeech: TextToSpeech? = null

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textToSpeech = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    Log.d("TextToSpeech", "Initialization Success")
                } else {
                    Log.d("TextToSpeech", "Initialization Failed")
                }
            }
            textToSpeech?.language = Locale.US

            with(binding) {
                magnifier.zoomInBtn.setOnClickListener {
                    increaseTextSize(textAppearance)
                }

                magnifier.zoomOutBtn.setOnClickListener {
                    decreaseTextSize(textAppearance)
                }
                editTextPhone.setEndIconOnClickListener {
                    textToSpeech?.speak("phone for user log in", TextToSpeech.QUEUE_FLUSH, null,"")

                }

                editTextPassword.setEndIconOnClickListener {
                    textToSpeech?.speak("password for user login", TextToSpeech.QUEUE_FLUSH, null,"")

                }
                btnContinue.setOnClickListener {
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }


    fun increaseTextSize(style: Int) {
        textAppearance = style
        var height = 64.asDp
        var textSize = 16f
        when (textAppearance) {
            R.style.InputTextLabel20 -> {
                textAppearance = R.style.InputTextLabel20
                height = 75.asDp
                textSize = 20f
            }

            R.style.InputTextLabel16 -> {
                textAppearance = R.style.InputTextLabel18
                height = 70.asDp
                textSize = 18f
            }

            R.style.InputTextLabel18 -> {
                textAppearance = R.style.InputTextLabel20
                height = 75.asDp
                textSize = 20f
            }

            R.style.InputTextLabel14 -> {
                textAppearance = R.style.InputTextLabel16
                height = 64.asDp
                textSize = 16f
            }
        }
        with(binding) {
            editTextName.layoutParams.height = height
            editTextName.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextName.setHintTextAppearance(textAppearance)
            editTextPhone.layoutParams.height = height
            editTextPhone.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextPhone.setHintTextAppearance(textAppearance)
            editTextPassword.layoutParams.height = height
            editTextPassword.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextPassword.setHintTextAppearance(textAppearance)
            btnContinue.textSize = textSize
        }
    }

    fun decreaseTextSize(style: Int) {
        textAppearance = style
        var height = 64.asDp
        var textSize = 16f
        when (textAppearance) {
            R.style.InputTextLabel16 -> {
                textAppearance = R.style.InputTextLabel14
                textSize = 14f
                height = 61.asDp
            }

            R.style.InputTextLabel18 -> {
                textAppearance = R.style.InputTextLabel16
                height = 64.asDp
                textSize = 16f
            }

            R.style.InputTextLabel20 -> {
                textAppearance = R.style.InputTextLabel18
                height = 70.asDp
                textSize = 18f
            }

            R.style.InputTextLabel14 -> {
                textAppearance = R.style.InputTextLabel14
                height = 61.asDp
                textSize = 14f
            }
        }
        with(binding) {
            editTextName.layoutParams.height = height
            editTextName.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextName.setHintTextAppearance(textAppearance)
            editTextPhone.layoutParams.height = height
            editTextPhone.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextPhone.setHintTextAppearance(textAppearance)
            editTextPassword.layoutParams.height = height
            editTextPassword.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            editTextPassword.setHintTextAppearance(textAppearance)
            btnContinue.textSize = textSize
        }
    }

}
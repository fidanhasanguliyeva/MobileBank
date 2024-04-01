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
import com.mobilebank.utils.decreaseTextAppearance
import com.mobilebank.utils.increaseTextAppearance
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
                    val triple = increaseTextAppearance(textAppearance)
                    textAppearance = triple.first
                    updateStyle(triple)
                }

                magnifier.zoomOutBtn.setOnClickListener {
                    val triple = decreaseTextAppearance(textAppearance)
                    textAppearance = triple.first
                    updateStyle(triple)
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

    fun updateStyle(triple: Triple<Int, Int, Float>) {
        with(binding) {
            editTextName.layoutParams.height = triple.second
            editTextName.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, triple.third)
            editTextName.setHintTextAppearance(triple.first)
            editTextPhone.layoutParams.height = triple.second
            editTextPhone.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, triple.third)
            editTextPhone.setHintTextAppearance(triple.first)
            editTextPassword.layoutParams.height = triple.second
            editTextPassword.editText?.setTextSize(TypedValue.COMPLEX_UNIT_SP, triple.third)
            editTextPassword.setHintTextAppearance(triple.first)
            btnContinue.textSize = triple.third
        }
    }

}
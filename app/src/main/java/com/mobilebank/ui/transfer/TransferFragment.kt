package com.mobilebank.ui.transfer

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.analytics.FirebaseAnalytics
import com.mobilebank.databinding.FragmentTransferBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.AnalyticsHelper
import com.mobilebank.utils.CardFormatWatcher
import com.mobilebank.utils.DecimalDigitsInputFilter
import com.mobilebank.utils.MainSharedPreferences
import com.mobilebank.utils.NumberTextWatcher
import com.mobilebank.utils.decreaseTextSize
import com.mobilebank.utils.increaseTextSize
import java.util.Locale
import kotlin.math.floor

class TransferFragment :
    BaseFragment<FragmentTransferBinding, TransferUiState, TransferViewModel>(TransferViewModel::class) {

    val args: TransferFragmentArgs by navArgs()
    var textToSpeech: TextToSpeech? = null

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding
        get() = FragmentTransferBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.d("TextToSpeech", "Initialization Success")
            } else {
                Log.d("TextToSpeech", "Initialization Failed")
            }
        }
        textToSpeech?.language = Locale.US

        with(binding) {
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            magnifier.btnHelp.isVisible = false
            magnifier.zoomInBtn.setOnClickListener {
                updateIncreaseUi(binding.root)
            }

            magnifier.zoomOutBtn.setOnClickListener {
                updateDecreaseUi(binding.root)
            }

            val card = args.card
            name.text = card?.name
            amount.text = card?.amount
            cardNumber.text = card?.cardNumber
            expireDate.text = card?.expiryDate
            editTextCard.editText?.addTextChangedListener(CardFormatWatcher(editTextCard.editText!!))
            editTextAmount.editText?.addTextChangedListener(NumberTextWatcher(editTextAmount.editText!!) {

            })
            binding.editTextCard.editText?.filters = arrayOf(DecimalDigitsInputFilter(20, 0))
            binding.editTextAmount.editText?.filters = arrayOf(DecimalDigitsInputFilter(10, 2))
            btnContinue.setOnClickListener {
                AnalyticsHelper.logEvent(FirebaseAnalytics.Event.PURCHASE, setSharedPrefData() ?: "")
                findNavController().navigate(TransferFragmentDirections.actionTransferFragmentToResultFragment())
            }

            editTextCard.setEndIconOnClickListener {
                textToSpeech?.speak(
                    "Enter card number, which you want to transfer to ",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    ""
                )

            }

            editTextAmount.setEndIconOnClickListener {
                textToSpeech?.speak("enter amount, that you want to transfer", TextToSpeech.QUEUE_FLUSH, null, "")

            }
        }
    }

    fun updateIncreaseUi(view: View?) {
        if (view is TextView) {
            val metrics = requireContext().resources.displayMetrics
            val textsize: Float = view.textSize / metrics.density
            val size = increaseTextSize(floor(textsize.toDouble()).toFloat())
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                updateIncreaseUi(innerView)
            }
        }
    }


    fun updateDecreaseUi(view: View?) {
        if (view is TextView) {
            val metrics = requireContext().resources.displayMetrics
            val textsize: Float = view.textSize / metrics.density
            val size = decreaseTextSize(floor(textsize.toDouble()).toFloat())
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                updateDecreaseUi(innerView)
            }
        }
    }

    fun setSharedPrefData(): String? {
        return MainSharedPreferences(context, "MAIN").get(
            "name",
            ""
        )
    }
}
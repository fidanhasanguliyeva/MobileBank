package com.mobilebank.ui.help

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.mobilebank.databinding.FragmentHelpBinding
import com.mobilebank.databinding.LayoutFaqItemBinding
import com.mobilebank.ui.base.CoreFragment
import com.mobilebank.utils.decreaseTextSize
import com.mobilebank.utils.increaseTextSize
import java.util.Locale
import kotlin.math.floor

class HelpFragment : CoreFragment<FragmentHelpBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHelpBinding
        get() = FragmentHelpBinding::inflate

    val list: List<Pair<String, String>> = listOf(
        Pair(
            "How can I sign up for mobile banking?",
            "You'll need your account details and may be asked to verify your identity.",
        ),
        Pair(
            "How can I transfer money between accounts?",
            "You can easily transfer money between your accounts within our mobile banking app. Simply navigate to the \"Transfer\" section and follow the prompts to complete your transfer securely."
        ),
        Pair(
            "How do I update my personal information in the app?",
            "You can update your personal information, such as your name or phone number, within the app's settings menu. Simply navigate to the \"Profile\" or \"Settings\" section to make changes as needed."
        ),
        Pair(
            "Is there a fee for using the mobile banking app?",
            "Our mobile banking app is free to download and use."
        ),
        Pair(
            "How can I contact customer support if I have a question or issue?",
            "If you have any questions or encounter any issues while using our mobile banking app, you can contact our customer support team directly through the app"
        ),
    )

    var textToSpeech: TextToSpeech? = null

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
            magnifier.btnHelp.isVisible = false

            magnifier.zoomInBtn.setOnClickListener {
                updateIncreaseUi(binding.linearLayout)
            }

            magnifier.zoomOutBtn.setOnClickListener {
                updateDecreaseUi(binding.linearLayout)
            }
            linearLayout.apply {
                removeAllViews()
                list.mapIndexed { index, map ->
                    val view = LayoutFaqItemBinding.inflate(layoutInflater)
                    view.txtQuestion.text = map.first
                    view.txtAnswer.text = map.second
                    view.imgLogo.setOnClickListener {
                        textToSpeech?.speak(
                            map.first + " " + map.second,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            ""
                        )
                    }

                    addView(view.root)
                }
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


    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.shutdown()
    }

}
package com.mobilebank.ui.home

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mobilebank.R
import com.mobilebank.databinding.FragmentHomeBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.MainSharedPreferences
import com.mobilebank.utils.asDp
import com.mobilebank.utils.collectLatestWithLifecycle
import com.mobilebank.utils.decreaseTextSize
import com.mobilebank.utils.increaseTextSize
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import kotlin.math.floor


@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeUiState, HomeViewModel>(HomeViewModel::class) {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    var textToSpeech: TextToSpeech? = null

    val adapter = TransactionsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCards()
        viewModel.getTransactions()

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.d("TextToSpeech", "Initialization Success")
            } else {
                Log.d("TextToSpeech", "Initialization Failed")
            }
        }
        textToSpeech?.language = Locale.US

        with(binding) {
            homeHeader.magnifier.zoomInBtn.setOnClickListener {
                updateIncreaseUi(binding.clLayout)
                updateIncreaseUi(binding.txtTransaction)
            }

            homeHeader.magnifier.zoomOutBtn.setOnClickListener {
                updateDecreaseUi(binding.root)
                updateDecreaseUi(binding.txtTransaction)
            }
            rvTransactions.layoutManager = LinearLayoutManager(context)
            rvTransactions.adapter = adapter

            var density = resources.displayMetrics.density;
            var pageMargin = 8 * density; // 8dp

            var viewPagerPadding = 20 + pageMargin;

            homeHeader.txtTitle.text = setSharedPrefData()
            homeHeader.txtSubtitle.text = "Have a good day"
            homeHeader.imgProfile.setImageResource(R.drawable.user)
            vpOnboarding.apply {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3

                val pageMargin = 16.asDp
                val offset = 24.asDp
                setPageTransformer { page, position ->
                    val pageOffset = position * -(2 * offset + pageMargin)
                    if (vpOnboarding.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                        if (ViewCompat.getLayoutDirection(vpOnboarding) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                            page.translationX = -pageOffset
                        } else {
                            page.translationX = pageOffset
                        }
                    } else {
                        page.translationY = pageOffset
                    }
                }
            }
            homeHeader.magnifier.btnHelp.isVisible = false
            vpOnboarding.setPadding(viewPagerPadding.toInt(), 0, viewPagerPadding.toInt(), 0)
        }

        viewModel.uiState.collectLatestWithLifecycle(viewLifecycleOwner) {
            with(binding) {
                vpOnboarding.adapter = CardsViewPagerAdapter(it.listOfCards, requireContext())
                diViewpager.attachTo(vpOnboarding)
                adapter.addTransactions(it.listOfTransactions)
                volume.setOnClickListener {
                    textToSpeech?.speak(
                        "Action Button for navigating to transfer screen",
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        ""
                    )
                }
                transferBtn.setOnClickListener { _ ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToTransferFragment(
                            it.listOfCards[adapter.currentPage]
                        )
                    )
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

    fun setSharedPrefData(): String? {
        return MainSharedPreferences(context, "MAIN").get(
            "name",
            ""
        )
    }
}
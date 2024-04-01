package com.mobilebank.ui.home

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mobilebank.R
import com.mobilebank.databinding.FragmentHomeBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.asDp
import com.mobilebank.utils.collectLatestWithLifecycle
import com.mobilebank.utils.decreaseTextSize
import com.mobilebank.utils.increaseTextSize
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeUiState, HomeViewModel>(HomeViewModel::class) {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    var textAppearance: Int = R.style.InputTextLabel16

    val adapter = TransactionsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCards()
        viewModel.getTransactions()
        with(binding) {
            homeHeader.magnifier.zoomInBtn.setOnClickListener {
                updateIncreaseUi(binding.clLayout)
            }

            homeHeader.magnifier.zoomOutBtn.setOnClickListener {
                updateDecreaseUi(binding.root)
            }
            rvTransactions.layoutManager = LinearLayoutManager(context)
            rvTransactions.adapter = adapter

            var density = resources.displayMetrics.density;
            var pageMargin = 8 * density; // 8dp

            var viewPagerPadding = 20 + pageMargin;

            homeHeader.txtTitle.text = "Name Surname"
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
            topUp.titleText = "Top up"
            topUp.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.plus)
            pay.titleText = "Pay"
            pay.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.bank_note_02)
            transfer.titleText = "Transfer"
            transfer.icon =
                AppCompatResources.getDrawable(requireContext(), R.drawable.switch_vertical_02)

        }

        viewModel.uiState.collectLatestWithLifecycle(viewLifecycleOwner) {
            Log.i("fidan", "onViewCreated: ${it.listOfCards}")
            with(binding) {
                vpOnboarding.adapter = CardsViewPagerAdapter(it.listOfCards, requireContext())
                diViewpager.attachTo(vpOnboarding)
                adapter.addTransactions(it.listOfTransactions)
                transfer.root.setOnClickListener { _ ->
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
            val size = increaseTextSize(textsize)
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
            val size = decreaseTextSize(textsize)
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                updateDecreaseUi(innerView)
            }
        }
    }

}
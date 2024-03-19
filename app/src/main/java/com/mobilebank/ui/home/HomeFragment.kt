package com.mobilebank.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.mobilebank.R
import com.mobilebank.databinding.FragmentHomeBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.asDp
import com.mobilebank.utils.collectLatestWithLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeUiState, HomeViewModel>(HomeViewModel::class) {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCards()

        with(binding) {
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
                vpOnboarding.adapter = CardsViewPagerAdapter(it.listOfCards)
                diViewpager.attachTo(vpOnboarding)
            }

        }

    }

    fun setHeight() {

    }
}
package com.mobilebank.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mobilebank.R
import com.mobilebank.databinding.FragmentHomeBinding
import com.mobilebank.ui.base.BaseFragment
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
            vpOnboarding
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
        }

    }

}
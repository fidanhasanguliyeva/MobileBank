package com.mobilebank.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobilebank.databinding.FragmentTransferBinding
import com.mobilebank.ui.base.BaseFragment

class TransferFragment :
    BaseFragment<FragmentTransferBinding, TransferUiState, TransferViewModel>(TransferViewModel::class) {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding
        get() = FragmentTransferBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}
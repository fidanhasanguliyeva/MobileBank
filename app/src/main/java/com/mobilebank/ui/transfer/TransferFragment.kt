package com.mobilebank.ui.transfer

import com.mobilebank.utils.CardFormatWatcher
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobilebank.databinding.FragmentTransferBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.DecimalDigitsInputFilter
import com.mobilebank.utils.NumberTextWatcher

class TransferFragment :
    BaseFragment<FragmentTransferBinding, TransferUiState, TransferViewModel>(TransferViewModel::class) {

    val args: TransferFragmentArgs by navArgs()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransferBinding
        get() = FragmentTransferBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
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
                findNavController().navigate(TransferFragmentDirections.actionTransferFragmentToResultFragment())
            }
        }
    }
}
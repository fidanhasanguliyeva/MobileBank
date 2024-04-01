package com.mobilebank.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilebank.R
import com.mobilebank.databinding.FragmentResultBinding
import com.mobilebank.ui.base.CoreFragment

class ResultFragment: CoreFragment<FragmentResultBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
    }
}
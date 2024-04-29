package com.mobilebank.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.mobilebank.R
import com.mobilebank.databinding.FragmentProfileBinding
import com.mobilebank.databinding.ListItemMenuBinding
import com.mobilebank.ui.base.BaseFragment
import com.mobilebank.utils.AnalyticsHelper
import com.mobilebank.utils.MainSharedPreferences
import com.mobilebank.utils.collectLatestWithLifecycle
import com.mobilebank.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileUiState, ProfileViewModel>(ProfileViewModel::class) {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.createUiList()

        viewModel.uiState.collectLatestWithLifecycle(viewLifecycleOwner) {
            if (it.listOfViews.isNotEmpty()) {
                with(binding) {
                    txtTitle.text = setSharedPrefData()
                    linearLayout.apply {
                        removeAllViews()
                        it.listOfViews.mapIndexed { index, model ->
                            val inflatedView = ListItemMenuBinding.inflate(layoutInflater)
                            inflatedView.imgIcon.load(model.icon)
                            inflatedView.txtTitle.text = model.title
                            addView(inflatedView.root)

                            inflatedView.root.setOnClickListener {
                                if (index == 0) {
                                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
                                }

                                if (index == 3) {
                                    findNavController().navigate(R.id.helpFragment)
                                }

                                if (index == 4) {
                                    MainSharedPreferences(context, "MAIN").set(
                                        "isLoggedIn",
                                        false
                                    )
                                    AnalyticsHelper.logEvent(FirebaseAnalytics.Event.LEVEL_END, setSharedPrefData() ?: "")
                                    findNavController().navigate(R.id.action_global_loginFragment)
                                }
                            }

                        }

                    }
                }
            }
        }
    }

    fun setSharedPrefData(): String? {
        return MainSharedPreferences(context, "MAIN").get(
            "name",
            ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.currentState.listOfViews.clear()
    }
}
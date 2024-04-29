package com.mobilebank.ui.profile

import com.mobilebank.R
import com.mobilebank.data.model.ProfileUiModel
import com.onsual.base.UiState

data class ProfileUiState(val listOfViews: MutableList<ProfileUiModel> = mutableListOf()) :
    UiState {


    fun returnMenuModels(): List<ProfileUiModel> {
        return listOf(
            ProfileUiModel(
                "Edit Account",
                R.drawable.ic_user,
            ),
            ProfileUiModel(
                "Rate us",
                R.drawable.ic_star,
            ),
            ProfileUiModel(
                "Share app",
                R.drawable.ic_share,
            ),
            ProfileUiModel(
                "FAQ",
                R.drawable.ic_message_question,
            ),
            ProfileUiModel(
                "Exit",
                R.drawable.ic_log_out,
            )
        )

    }

}

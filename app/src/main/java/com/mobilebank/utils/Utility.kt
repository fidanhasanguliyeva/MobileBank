package com.mobilebank.utils

import android.content.Context
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mobilebank.R

fun increaseTextAppearance(style: Int): Triple<Int, Int, Float> {
    var textAppearance = style
    var height = 64.asDp
    var textSize = 16f
    when (textAppearance) {
        R.style.InputTextLabel20 -> {
            textAppearance = R.style.InputTextLabel20
            height = 75.asDp
            textSize = 20f
        }

        R.style.InputTextLabel16 -> {
            textAppearance = R.style.InputTextLabel18
            height = 70.asDp
            textSize = 18f
        }

        R.style.InputTextLabel18 -> {
            textAppearance = R.style.InputTextLabel20
            height = 75.asDp
            textSize = 20f
        }

        R.style.InputTextLabel14 -> {
            textAppearance = R.style.InputTextLabel16
            height = 64.asDp
            textSize = 16f
        }
    }
    return Triple(textAppearance, height, textSize)
}

fun increaseTextSize(size: Float): Float {
    var textSize = size
    when (textSize) {
        20f -> {
            textSize = 20f
        }

        16f -> {
            textSize = 18f
        }

        18f -> {
            textSize = 20f
        }

        14f -> {
            textSize = 16f
        }
    }
    return textSize
}

fun decreaseTextSize(size: Float): Float {
    var textSize = size
    when (textSize) {
        20f -> {
            textSize = 18f
        }

        16f -> {
            textSize = 14f
        }

        18f -> {
            textSize = 16f
        }

        14f -> {
            textSize = 14f
        }
    }
    return textSize
}

fun decreaseTextAppearance(style: Int): Triple<Int, Int, Float> {
    var textAppearance = style
    var height = 64.asDp
    var textSize = 16f
    when (textAppearance) {
        R.style.InputTextLabel16 -> {
            textAppearance = R.style.InputTextLabel14
            textSize = 14f
            height = 61.asDp
        }

        R.style.InputTextLabel18 -> {
            textAppearance = R.style.InputTextLabel16
            height = 64.asDp
            textSize = 16f
        }

        R.style.InputTextLabel20 -> {
            textAppearance = R.style.InputTextLabel18
            height = 70.asDp
            textSize = 18f
        }

        R.style.InputTextLabel14 -> {
            textAppearance = R.style.InputTextLabel14
            height = 61.asDp
            textSize = 14f
        }
    }
    return Triple(textAppearance, height, textSize)
}


fun Fragment.showSnackBar(context: Context?, text: String) {
    context?.let {
        val resourceId: Int =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        val bottomBarHeight =
            if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
        val snackbar = this.view?.let { it1 -> Snackbar.make(it1, text, Snackbar.LENGTH_LONG) }
        val snackbarView = snackbar?.view
        snackbarView?.elevation = 1000F
        snackbarView?.setBackgroundResource(R.drawable.bg_round_corner_primary_light)
        val params = snackbarView?.layoutParams as FrameLayout.LayoutParams
        snackbarView.setOnApplyWindowInsetsListener { view, insets ->
            view.setMargins(16, 0, 16, params.bottomMargin + bottomBarHeight)
            insets
        }
        snackbarView.layoutParams = params
        snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snackbar.show()
    }
}
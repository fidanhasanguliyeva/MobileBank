package com.mobilebank.utils

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

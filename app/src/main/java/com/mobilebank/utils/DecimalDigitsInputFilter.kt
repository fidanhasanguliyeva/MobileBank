package com.mobilebank.utils

import android.text.InputFilter
import android.text.Spanned


class DecimalDigitsInputFilter(val digitsBeforeZero: Int, val digitsAfterZero: Int) : InputFilter {

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        val sb = StringBuilder(dest)
        val endIndex = if (end <= sb.toString().length) end else sb.toString().length
        sb.replace(dstart, dend, source.subSequence(start, endIndex).toString())

        val str = sb.toString()

        val decimalIndex = str.indexOf('.')
        if (decimalIndex != -1 && str.length - decimalIndex - 1 > digitsAfterZero) {
            return EMPTY
        }

        if (decimalIndex == -1 && str.length >= digitsBeforeZero) {
            return if (source.isNotEmpty()) {
                EMPTY
            } else {
                null
            }
        }

        if (decimalIndex != -1 && decimalIndex >= digitsBeforeZero) {
            return if (source.isNotEmpty()) {
                EMPTY
            } else {
                null
            }
        }

        return null
    }

    companion object {
        const val EMPTY: String = ""
    }
}
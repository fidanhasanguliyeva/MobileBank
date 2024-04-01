package com.mobilebank.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.util.*

open class NumberTextWatcher(
    private val et: EditText,
    private val changeFractionalPart: Boolean = true,
    private val onItemChangeListener: (String) -> Unit
) :
    TextWatcher {

    private var decimalCount = MINUS_ONE

    private val symbols = DecimalFormatSymbols(Locale.US)
    private val df: DecimalFormat = DecimalFormat(DECIMAL_FORMAT, symbols)
    private val dfnd: DecimalFormat = DecimalFormat(DECIMAL_FORMAT_NON_FRACTION, symbols)

    private var hasFractionalPart: Boolean = false
    private var trailingZeroCount: Int = ZERO

    init {
        df.isDecimalSeparatorAlwaysShown = true
        hasFractionalPart = false
    }

    override fun afterTextChanged(s: Editable) {
        et.removeTextChangedListener(this)
        val decimalFormatString = StringBuilder()
        decimalFormatString.append(DECIMAL_FORMAT_DOT)
        var decimalCountWithoutZero = decimalCount - trailingZeroCount
        while (decimalCountWithoutZero-- > ZERO) {
            decimalFormatString.append(SHARP)
        }
        val dynamicDecimalFormat =
            DecimalFormat(decimalFormatString.toString(), symbols)
        dynamicDecimalFormat.isDecimalSeparatorAlwaysShown = true

        if (et.text.toString() == DOT) {
            et.setText(ZERO_DOT)
            et.setSelection(TWO)
        } else {
            try {
                val inilen: Int = et.text.length
                val v =
                    s.toString().replace(df.decimalFormatSymbols.groupingSeparator.toString(), "")
                val n = dynamicDecimalFormat.parse(v)
                val cp = et.selectionStart
                setFractionPart(dynamicDecimalFormat, n)
                val endlen: Int = et.text.length
                val sel = cp + (endlen - inilen)
                if (sel > ZERO && sel <= et.text.length) {
                    et.setSelection(sel)
                } else if (et.text.length > ZERO) {
                    // place cursor at the end?
                    et.setSelection(et.text.length - ONE)
                }
            } catch (nfe: NumberFormatException) {
                // do nothing?
            } catch (e: ParseException) {
                // do nothing?
            }
        }

        onItemChangeListener.invoke(et.text.toString())
        et.addTextChangedListener(this)
    }

    private fun setFractionPart(dynamicDecimalFormat: DecimalFormat, n: Number?) {
        if (hasFractionalPart) {
            val trailingZeros = StringBuilder()
            while (trailingZeroCount-- > ZERO)
                trailingZeros.append(ZERO_CHAR)
            val nn = dynamicDecimalFormat.format(n) + trailingZeros.toString()
            et.setText(nn)
        } else {
            et.setText(dfnd.format(n))
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        //beforeTextChanged
    }

    override fun onTextChanged(ss: CharSequence, start: Int, before: Int, count: Int) {

        val amount = ss.toString()

        hasFractionalPart = amount.contains(df.decimalFormatSymbols.decimalSeparator.toString())


        var index = amount.indexOf(df.decimalFormatSymbols.decimalSeparator.toString())
        trailingZeroCount = ZERO
        if (index > MINUS_ONE) {
            decimalCount = amount.length - index - ONE
            index++
            var decimalLimit = decimalCount
            while (index < amount.length && decimalLimit > ZERO) {
                if (amount[index] == ZERO_CHAR)
                    trailingZeroCount++
                else {
                    trailingZeroCount = ZERO
                }
                index++
                decimalLimit--
            }

            if (amount.length > index && amount[index] > FIVE) {
                trailingZeroCount = ZERO
            }

            hasFractionalPart = true
        } else {
            decimalCount = MINUS_ONE
            hasFractionalPart = false
        }

    }

    companion object {
        private val MINUS_ONE = -1
        private val ZERO = 0
        private val ONE = 1
        private val TWO = 2
        private val FIVE = '5'
        private val ZERO_CHAR = '0'
        private val SHARP = "#"
        private val DOT = "."
        private val ZERO_DOT = "0."
        private val DECIMAL_FORMAT = "#,###.##"
        private val DECIMAL_FORMAT_NON_FRACTION = "#,###"
        private val DECIMAL_FORMAT_DOT = "#,###."
    }

}
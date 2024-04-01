package com.mobilebank.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class CardFormatWatcher(private val editText: EditText) : TextWatcher {
    private var isFormatting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // No implementation needed
    }

    override fun afterTextChanged(editable: Editable?) {
        if (isFormatting) {
            return
        }


        isFormatting = true

        val cursorPosition = editText.selectionStart // Get the cursor position before formatting

        // Remove any non-digit characters
        val digitsOnly = editable.toString().replace("\\D".toRegex(), "")

        val formatted = formatCardNumber(digitsOnly)

        editText.setText(formatted)

        // Adjust cursor position after formatting
        var newCursorPosition = cursorPosition
        if (cursorPosition > formatted.length) {
            newCursorPosition = formatted.length
        } else if (cursorPosition > 0 && cursorPosition % 5 == 0) {
            // If cursor is at a space, move it to the next position
            newCursorPosition++
        }
        editText.setSelection(newCursorPosition)

        isFormatting = false
    }

    private fun formatCardNumber(cardNumber: String): String {
        val formatted = StringBuilder()

        for (i in cardNumber.indices) {
            formatted.append(cardNumber[i])
            if ((i + 1) % 4 == 0 && i != cardNumber.length - 1) {
                formatted.append(" ") // Add space after every 4 digits
            }
        }

        return formatted.toString()
    }
}


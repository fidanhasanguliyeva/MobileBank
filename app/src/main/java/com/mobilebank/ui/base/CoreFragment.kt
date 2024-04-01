package com.mobilebank.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base class for all [Fragment] instances
 */
abstract class CoreFragment<VB : ViewBinding>:
    Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB


    protected val binding: VB
        get() = requireNotNull(_binding)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

//    fun handleEvent(event: Event.Alert) {
//        val dialog = GenericBottomDialog.Builder().titleText(event.title)
//            .descriptionText(event.message)
//            .mainBtnText(getString(R.string.btn_close))
//            .dialogType(DialogType.ERROR.name)
//            .isCancelable(true)
//            .build()
//        dialog.show(childFragmentManager, GenericBottomDialog::class.java.canonicalName)
//    }

    @SuppressLint("ClickableViewAccessibility")
    fun enableHideKeyboardWhenTouch(view: View?, action: () -> Unit = {}) {
        if (view !is EditText && view?.tag?.toString() != NOT_DISMISS) {
            view?.setOnTouchListener { _, _ ->
                hideSoftKeyboard()
                action.invoke()

                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                enableHideKeyboardWhenTouch(innerView)
            }
        }
    }


    fun hideSoftKeyboard() {
        try {
            val inputMethodManager =
                context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager?
            inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    companion object {
        const val NOT_DISMISS = "notDismissKeyboard"
    }
}
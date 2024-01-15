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
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.mobilebank.R
import com.onsual.base.BaseViewModel
import com.onsual.base.Event
import com.onsual.base.UiState
//import com.onsual.model.DialogType
import kotlin.reflect.KClass


/**
 * Base class for all [Fragment] instances
 */
abstract class BaseFragment<VB : ViewBinding, S : UiState, VM : BaseViewModel<S>>(viewModelClass: KClass<VM>) :
    Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB


    protected val binding: VB
        get() = requireNotNull(_binding)

    open val viewModelFactoryOwner: (() -> ViewModelStoreOwner) = { this }

    open val factoryProducer: ViewModelProvider.Factory by lazy { defaultViewModelProviderFactory }

    open val viewModel: VM by createViewModelLazy(
        viewModelClass,
        { viewModelFactoryOwner.invoke().viewModelStore },
        factoryProducer = { factoryProducer })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.event.observe(this) {
//            handleEvent(it as Event.Alert)
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun handleEvent(event: Event.Alert) {
//        val dialog = GenericBottomDialog.Builder().titleText(event.title)
//            .descriptionText(event.message)
//            .mainBtnText(getString(R.string.btn_close))
//            .dialogType(DialogType.ERROR.name)
//            .isCancelable(true)
//            .build()
//        dialog.show(childFragmentManager, GenericBottomDialog::class.java.canonicalName)
    }

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
package com.mobilebank.utils

import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val Int.asDp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun <T> Flow<T>.collectLatestWithLifecycle(
    viewLifecycleOwner: LifecycleOwner,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minState) {
            collectLatest { value ->
                action(value)
            }
        }
    }
}

fun <T> Flow<T>.collectWithLifecycle(
    viewLifecycleOwner: LifecycleOwner,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minState) {
            collect { value ->
                action(value)
            }
        }
    }
}
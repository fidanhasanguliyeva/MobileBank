package com.mobilebank.utils

import android.app.Activity
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import javax.sql.DataSource

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

fun ImageView.load(string: String, onLoad: () -> Unit = {}, onError: () -> Unit = {}) {
    Glide.with(context)
        .load(string)
        .listener(object : RequestListener<Drawable> {

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }


        })
        .into(this)
}

fun ImageView.load(uri: Uri) {
    Glide.with(context)
        .load(uri)
        .into(this)
}

fun ImageView.load(drawable: Drawable) {
    Glide.with(context)
        .load(drawable)
        .into(this)
}

fun ImageView.load(file: File) {
    Glide.with(context)
        .load(file)
        .into(this)
}

fun ImageView.load(drawable: Int) {
    Glide.with(context)
        .load(drawable)
        .into(this)
}

fun ImageView.load(drawable: Int?) {
    Glide.with(context)
        .load(drawable)
        .into(this)
}

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        left ?: params.leftMargin,
        top ?: params.topMargin,
        right ?: params.rightMargin,
        bottom ?: params.bottomMargin
    )
    layoutParams = params
}

fun getStatusBarHeight(resources: Resources?, activity: Activity): Int {
    var height = 0
    val idStatusBarHeight: Int =
        resources?.getIdentifier("status_bar_height", "dimen", "android")
            ?: 0
    if (idStatusBarHeight > 0) {
        height = resources?.getDimensionPixelSize(idStatusBarHeight) ?: 0
    }
    val rectangle = Rect()
    val window: Window = activity.window
    window.decorView.getWindowVisibleDisplayFrame(rectangle)
    return rectangle.top
}

fun getNavigationBarHeight(resources: Resources?): Int {
    val resourceId = resources?.getIdentifier("navigation_bar_height", "dimen", "android") ?: 0
    return if (resourceId > 0) {
        resources?.getDimensionPixelSize(resourceId) ?: 0
    } else 0
}
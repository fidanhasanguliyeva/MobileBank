package com.mobilebank.utils

import android.content.res.Resources

val Int.asDp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

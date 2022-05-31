package com.tinyfight.gweather.feature.util

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.tinyfight.gweather.GWeatherApplication

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.util.ResourceUtil
 */
fun getString(@StringRes string: Int, vararg data: Any): String {
    return GWeatherApplication.application.getString(string, *data)
}

fun getDrawable(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(GWeatherApplication.application, resId)
}
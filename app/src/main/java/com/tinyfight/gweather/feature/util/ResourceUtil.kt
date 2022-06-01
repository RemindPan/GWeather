package com.tinyfight.gweather.feature.util

import androidx.annotation.StringRes
import com.tinyfight.gweather.GWeatherApplication

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.util.ResourceUtil
 */
fun getString(@StringRes string: Int, vararg data: Any): String {
    return GWeatherApplication.application.getString(string, *data)
}

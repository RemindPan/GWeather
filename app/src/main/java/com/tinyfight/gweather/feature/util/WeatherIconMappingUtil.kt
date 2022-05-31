package com.tinyfight.gweather.feature.util

import android.graphics.drawable.Drawable
import com.tinyfight.gweather.R

/**
 * Create at 2022/6/1
 * @author Yao
 * Name com.tinyfight.gweather.data.util.WeatherIconMappingUtil
 */

const val CLEAR_DAY = "clear-day"
const val CLEAR_NIGHT = "clear-night"
const val RAIN = "rain"
const val SNOW = "snow"
const val SLEET = "sleet"
const val WIND = "wind"
const val FOG = "fog"
const val CLOUDY = "cloudy"
const val PARTLY_CLOUDY_DAY = "partly-cloudy-day"
const val PARTLY_CLOUDY_NIGHT = "partly-cloudy-night"

fun mapIconToDrawable(icon: String): Int {
    return when (icon) {
        CLEAR_DAY -> {
            R.drawable.ic_sunny
        }
        CLEAR_NIGHT -> {
            R.drawable.ic_moon
        }
        RAIN -> {
            R.drawable.ic_rain
        }
        SNOW -> {
            R.drawable.ic_snow
        }
        SLEET -> {
            R.drawable.ic_wet_flurries
        }
        WIND -> {
            R.drawable.ic_windy
        }
        FOG -> {
            R.drawable.ic_fog
        }
        CLOUDY -> {
            R.drawable.ic_cloudy
        }
        PARTLY_CLOUDY_DAY -> {
            R.drawable.ic_mostly_sunny
        }
        PARTLY_CLOUDY_NIGHT -> {
            R.drawable.ic_night_mostly_cloudy
        }
        else -> {
            R.drawable.ic_sunny
        }
    }
}

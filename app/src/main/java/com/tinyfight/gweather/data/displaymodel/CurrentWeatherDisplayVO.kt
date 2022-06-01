package com.tinyfight.gweather.data.displaymodel

import com.tinyfight.gweather.data.util.timeStampToMinute
import com.tinyfight.gweather.domain.model.HourlyWeatherVO
import com.tinyfight.gweather.domain.model.VO

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.data.model.CurrentWeatherDisplayVO
 * This is view display entity which depend on domain/vo/weathervo,
 * will convert business entity to display entity for UI display
 */
data class CurrentWeatherDisplayVO(
    val highTemperature: Double,
    val lowTemperature: Double,
    val currentTemperature: Double,
    val icon: String,
    val day: String,
) : VO

fun fromWeatherVOToCurrentWeatherDisplayVO(
    currentVO: HourlyWeatherVO,
    dailyWeatherDisplayVO: DailyWeatherDisplayVO,
): CurrentWeatherDisplayVO {
    return CurrentWeatherDisplayVO(
        highTemperature = dailyWeatherDisplayVO.highTemperature,
        lowTemperature = dailyWeatherDisplayVO.lowTemperature,
        currentTemperature = currentVO.temperature,
        icon = currentVO.icon,
        day = currentVO.time.timeStampToMinute()
    )
}

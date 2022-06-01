package com.tinyfight.gweather.data.displaymodel

import com.tinyfight.gweather.data.util.timeStampToDate
import com.tinyfight.gweather.domain.model.DailyWeatherVO
import com.tinyfight.gweather.domain.model.VO

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.data.model.DailyWeatherDisplayVO
 */
data class DailyWeatherDisplayVO(
    val highTemperature: Double,
    val lowTemperature: Double,
    val icon: String,
    val day: String,
) : VO

fun fromDailyWeatherVOToDisplayVO(vo: DailyWeatherVO): DailyWeatherDisplayVO {
    return DailyWeatherDisplayVO(
        highTemperature = vo.temperatureHigh,
        lowTemperature = vo.temperatureLow,
        icon = vo.icon,
        day = vo.time.timeStampToDate(),
    )
}

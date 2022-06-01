package com.tinyfight.gweather.data.displaymodel

import com.tinyfight.gweather.data.util.timeStampGetHour
import com.tinyfight.gweather.domain.model.HourlyWeatherVO
import com.tinyfight.gweather.domain.model.VO

/**
 * Create at 2022/6/1
 * @author Yao
 * Name com.tinyfight.gweather.data.model.HourlyWeatherDisplayVO
 * This is view display entity which depend on domain/vo/weathervo,
 * will convert business entity to display entity for UI display
 */
data class HourlyWeatherDisplayVO(
    val temperature: Double,
    val icon: String,
    val hour: Int,
) : VO

fun fromHourlyWeatherVOToDisplayVO(hourlyWeatherVO: HourlyWeatherVO): HourlyWeatherDisplayVO {
    return HourlyWeatherDisplayVO(
        temperature = hourlyWeatherVO.temperature,
        icon = hourlyWeatherVO.icon,
        hour = hourlyWeatherVO.time.timeStampGetHour()
    )
}

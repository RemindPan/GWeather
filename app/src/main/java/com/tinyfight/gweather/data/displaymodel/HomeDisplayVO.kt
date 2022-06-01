package com.tinyfight.gweather.data.displaymodel

import com.tinyfight.gweather.domain.model.VO

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.data.model.HomeDisplayVO
 * This is view display entity which depend on domain/vo/weathervo,
 * will convert business entity to display entity for UI display
 */
data class HomeDisplayVO(
    val currentWeatherDisplayVO: CurrentWeatherDisplayVO,
    val dailyWeatherDisplayVOList: List<DailyWeatherDisplayVO>,
) : VO

package com.tinyfight.gweather.data.model

import com.tinyfight.gweather.domain.model.VO

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.data.model.HomeDisplayVO
 */
data class HomeDisplayVO(
    val currentWeatherDisplayVO: CurrentWeatherDisplayVO,
    val dailyWeatherDisplayVOList: List<DailyWeatherDisplayVO>,
) : VO

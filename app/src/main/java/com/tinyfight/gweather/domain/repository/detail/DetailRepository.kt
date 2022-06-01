package com.tinyfight.gweather.domain.repository.detail

import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.domain.model.WeatherResponseVO

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.domain.repository.detail.DetailRepository
 */
interface DetailRepository {
    suspend fun requestHourlyWeather(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherResponseVO>
}
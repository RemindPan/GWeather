package com.tinyfight.gweather.domain.repository.home

import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.data.vo.WeatherResponseVO

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.domain.repository.home.HomeRepository
 */
interface HomeRepository {
    suspend fun getWeatherByLocation(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherResponseVO>
}
package com.tinyfight.gweather.data.repository.detail

import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.data.network.api.GWeatherApi
import com.tinyfight.gweather.data.network.safeApiCall
import com.tinyfight.gweather.domain.model.WeatherResponseVO
import com.tinyfight.gweather.domain.repository.detail.DetailRepository

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.repository.detail.DetailRepositoryImpl
 * This is impl class for repository which defined in domain/repository
 */
class DetailRepositoryImpl : DetailRepository {
    override suspend fun requestHourlyWeather(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherResponseVO> = safeApiCall {
        GWeatherApi.weatherApi.getWeatherByLocation(latitude = latitude,
            longitude = longitude,
            excludeList = listOf("flags, currently, minutely, alerts, daily, flags"))
    }
}
package com.tinyfight.gweather.data.repository.home

import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.data.network.api.GWeatherApi
import com.tinyfight.gweather.data.network.safeApiCall
import com.tinyfight.gweather.domain.model.WeatherResponseVO
import com.tinyfight.gweather.domain.repository.home.HomeRepository

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.repository.home.HomeRepositoryImpl
 * This is impl class for repository which defined in domain/repository
 */
class HomeRepositoryImpl : HomeRepository {
    override suspend fun getWeatherByLocation(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherResponseVO> = safeApiCall {
        GWeatherApi.weatherApi.getWeatherByLocation(latitude, longitude)
    }
}
package com.tinyfight.gweather.data.network.api

import com.tinyfight.gweather.data.network.NetworkClient
import com.tinyfight.gweather.data.vo.WeatherResponseVO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.network.api.GWeatherApi
 */
interface GWeatherApi {
    @GET("/{latitude},{longitude}")
    suspend fun getWeatherByLocation(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Query("exclude") list: List<String> = listOf("flags"),
    ): WeatherResponseVO

    companion object {
        val weatherApi: GWeatherApi = NetworkClient.getApi(GWeatherApi::class.java)
    }
}
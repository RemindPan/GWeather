package com.tinyfight.gweather.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.vo.WeatherVO
 */
@Suppress("unused")
data class WeatherResponseVO(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val currently: HourlyWeatherVO? = null,
    val minutely: MinutelyVO? = null,
    val hourly: HourlyVO? = null,
    val daily: DailyVO? = null,
    val alerts: List<AlertVO>? = null,
) : VO

/**
 * MinutelyVO
 * This is minutely field in the whole response
 */
data class MinutelyVO(
    val summary: String = "",
    val icon: String = "",
    val data: List<MinutelyWeatherVO>?,
) : VO

/**
 * MinutelyWeatherVO
 * This is item weather data in the MinutelyVO
 */
data class MinutelyWeatherVO(
    val time: Long,
    val precipIntensity: Double = 0.0,
    val precipIntensityError: Double = 0.0,
    val precipProbability: Double = 0.0,
    val precipType: String = "",
) : VO

/**
 * HourlyVO
 * This is hourly field in the whole response
 */
data class HourlyVO(
    val summary: String = "",
    val icon: String = "",
    val data: List<HourlyWeatherVO>?,
) : VO

/**
 * HourlyWeatherVO
 * This is item weather data in the HourlyVO
 */
data class HourlyWeatherVO(
    val time: Long,
    val summary: String = "",
    val icon: String = "",
    val precipIntensity: Double = 0.0,
    val precipProbability: Double = 0.0,
    val precipType: String = "",
    val precipIntensityError: Double = 0.0,
    val temperature: Double = 0.0,
    val apparentTemperature: Double = 0.0,
    val dewPoint: Double = 0.0,
    val humidity: Double = 0.0,
    val pressure: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windGust: Double = 0.0,
    val windBearing: Double = 0.0,
    val cloudCover: Double = 0.0,
    val uvIndex: Int = 0,
    val visibility: Double = 0.0,
    val ozone: Double = 0.0,
) : VO

/**
 * DailyVO
 * This is daily field in the whole response
 */
data class DailyVO(
    val summary: String = "",
    val icon: String = "",
    val data: List<DailyWeatherVO>?,
) : VO

/**
 * DailyWeatherVO
 * This is item weather data in the HourlyVO
 */
data class DailyWeatherVO(
    val time: Long,
    val sunriseTime: Long = 0,
    val sunsetTime: Long = 0,
    val summary: String = "",
    val icon: String = "",
    val moonPhase: Double = 0.0,
    val precipIntensity: Double = 0.0,
    val precipIntensityMax: Double = 0.0,
    val precipIntensityMaxTime: Double = 0.0,
    val precipProbability: Double = 0.0,
    val precipType: String = "",
    val temperatureHigh: Double = 0.0,
    val temperatureHighTime: Double = 0.0,
    val temperatureLow: Double = 0.0,
    val temperatureLowTime: Double = 0.0,
    val apparentTemperatureHigh: Double = 0.0,
    val apparentTemperatureHighTime: Double = 0.0,
    val apparentTemperatureLow: Double = 0.0,
    val apparentTemperatureLowTime: Double = 0.0,
    val dewPoint: Double = 0.0,
    val humidity: Double = 0.0,
    val pressure: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windGust: Double = 0.0,
    val windGustTime: Long = 0,
    val windBearing: Double = 0.0,
    val cloudCover: Double = 0.0,
    val uvIndex: Long = 0,
    val uvIndexTime: Long = 0,
    val visibility: Double = 0.0,
    val ozone: Double = 0.0,
    val temperatureMin: Double = 0.0,
    val temperatureMinTime: Long = 0,
    val temperatureMax: Double = 0.0,
    val temperatureMaxTime: Long = 0,
    val apparentTemperatureMin: Double = 0.0,
    val apparentTemperatureMinTime: Long = 0,
    val apparentTemperatureMax: Double = 0.0,
    val apparentTemperatureMaxTime: Long = 0,
) : VO

data class AlertVO(
    val title: String = "",
    val time: Long = 0,
    val expires: Long = 0,
    val description: String = "",
    val uri: String = "",
) : VO

@Suppress("unused")
data class FlagVO(
    @SerializedName("darksky-unavailable")
    val unavailable: Boolean = false,
    @SerializedName("nearest-station")
    val nearestStation: String = "",
    val units: String = "",
    val sources: List<String>? = null,
)
package com.tinyfight.gweather.feature.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinyfight.gweather.data.displaymodel.*
import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.domain.repository.detail.DetailRepository
import kotlinx.coroutines.launch

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.detail.viewmodel.DetailViewModel
 */
class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {
    val dailyWeatherLiveData = MutableLiveData<List<HourlyWeatherDisplayVO>?>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private fun showLoading() {
        loadingLiveData.value = true
    }

    private fun hideLoading() {
        loadingLiveData.value = false
    }

    fun requestHourlyWeather(
        latitude: Double,
        longitude: Double,
    ) {
        showLoading()
        viewModelScope.launch {
            val result = detailRepository.requestHourlyWeather(latitude, longitude)
            if (result is Result.Success) {
                val hourlyWeatherVOList = result.data.hourly?.data ?: return@launch
                if (hourlyWeatherVOList.isEmpty()) {
                    return@launch
                }

                val hourlyWeatherDisplayVO = hourlyWeatherVOList.map {
                    fromHourlyWeatherVOToDisplayVO(it)
                }

                dailyWeatherLiveData.value = hourlyWeatherDisplayVO
            } else {
                dailyWeatherLiveData.value = null
            }
            hideLoading()
        }
    }
}
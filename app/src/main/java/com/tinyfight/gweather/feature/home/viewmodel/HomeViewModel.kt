package com.tinyfight.gweather.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.data.vo.DailyVO
import com.tinyfight.gweather.domain.repository.home.HomeRepository
import kotlinx.coroutines.launch

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.viewmodel.HomeViewModel
 */
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    val dailyWeatherLiveData = MutableLiveData<DailyVO>()

    fun getWeatherByLocation(
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            val result = homeRepository.getWeatherByLocation(latitude, longitude)
            if (result is Result.Success) {
                dailyWeatherLiveData.value = result.data.daily
            } else {
                dailyWeatherLiveData.value = null
            }
        }
    }
}
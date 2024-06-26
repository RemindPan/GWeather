package com.tinyfight.gweather.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinyfight.gweather.data.displaymodel.HomeDisplayVO
import com.tinyfight.gweather.data.displaymodel.fromDailyWeatherVOToDisplayVO
import com.tinyfight.gweather.data.displaymodel.fromWeatherVOToCurrentWeatherDisplayVO
import com.tinyfight.gweather.data.network.Result
import com.tinyfight.gweather.domain.repository.home.HomeRepository
import kotlinx.coroutines.launch

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.viewmodel.HomeViewModel
 */
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    val dailyWeatherLiveData = MutableLiveData<HomeDisplayVO?>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private fun showLoading() {
        loadingLiveData.value = true
    }

    private fun hideLoading() {
        loadingLiveData.value = false
    }

    fun getWeatherByLocation(
        latitude: Double,
        longitude: Double,
    ) {
        showLoading()
        viewModelScope.launch {
            val result = homeRepository.getWeatherByLocation(latitude, longitude)
            if (result is Result.Success) {
                val current = result.data.currently ?: return@launch
                val dailyList = result.data.daily?.data ?: return@launch

                if (dailyList.isEmpty()) {
                    return@launch
                }

                val dailyWeatherDisplayVOList = dailyList.map {
                    fromDailyWeatherVOToDisplayVO(it)
                }

                val displayVO = HomeDisplayVO(
                    dailyWeatherDisplayVOList = dailyWeatherDisplayVOList,
                    currentWeatherDisplayVO = fromWeatherVOToCurrentWeatherDisplayVO(
                        current,
                        dailyWeatherDisplayVOList[0]
                    )
                )
                dailyWeatherLiveData.value = displayVO
            } else {
                dailyWeatherLiveData.value = null
            }
            hideLoading()
        }
    }
}
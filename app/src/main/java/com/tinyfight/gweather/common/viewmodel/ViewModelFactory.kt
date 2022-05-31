@file:Suppress("UNCHECKED_CAST")

package com.tinyfight.gweather.common.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.tinyfight.gweather.data.repository.detail.DetailRepositoryImpl
import com.tinyfight.gweather.data.repository.home.HomeRepositoryImpl
import com.tinyfight.gweather.feature.detail.viewmodel.DetailViewModel
import com.tinyfight.gweather.feature.home.viewmodel.HomeViewModel
import java.lang.IllegalArgumentException

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.common.viewmodel.ViewModelFactory
 */
class ViewModelFactory constructor(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ) = with(modelClass) {
        when {
            isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepositoryImpl())
            }
            isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(DetailRepositoryImpl())
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    } as T
}
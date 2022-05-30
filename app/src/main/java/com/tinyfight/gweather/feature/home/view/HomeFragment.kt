package com.tinyfight.gweather.feature.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tinyfight.gweather.common.ViewModelFactory
import com.tinyfight.gweather.common.base.ViewBindingFragment
import com.tinyfight.gweather.databinding.FragmentHomeBinding
import com.tinyfight.gweather.feature.home.viewmodel.HomeViewModel

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.view.HomeFragment
 */
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    private val mainViewModel by viewModels<HomeViewModel> { ViewModelFactory(this) }

    override fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }


}
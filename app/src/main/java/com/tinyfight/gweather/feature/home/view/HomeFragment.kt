package com.tinyfight.gweather.feature.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tinyfight.gweather.common.base.ViewBindingFragment
import com.tinyfight.gweather.databinding.FragmentHomeBinding

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.view.HomeFragment
 */
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {

    override fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }


}
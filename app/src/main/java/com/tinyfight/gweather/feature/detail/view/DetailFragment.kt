package com.tinyfight.gweather.feature.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tinyfight.gweather.common.base.LocationFragment
import com.tinyfight.gweather.common.viewmodel.ViewModelFactory
import com.tinyfight.gweather.databinding.FragmentDetailBinding
import com.tinyfight.gweather.feature.detail.viewmodel.DetailViewModel

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.detail.view.DetailFragment
 */
class DetailFragment : LocationFragment<FragmentDetailBinding>() {
    private val detailViewModel by viewModels<DetailViewModel> { ViewModelFactory(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    override fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ) = FragmentDetailBinding.inflate(layoutInflater, container, false)

    override fun onLocationPermissionGranted() {

    }

    private fun observeLiveData() {
//        detailViewModel.observe(viewLifecycleOwner) {
//
//        }
    }
}
package com.tinyfight.gweather.feature.detail.view

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinyfight.gweather.common.base.LocationFragment
import com.tinyfight.gweather.common.viewmodel.ViewModelFactory
import com.tinyfight.gweather.databinding.FragmentDetailBinding
import com.tinyfight.gweather.feature.detail.viewmodel.DetailViewModel
import com.tinyfight.gweather.feature.detail.widget.HourRecyclerViewAdapter

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.detail.view.DetailFragment
 */
class DetailFragment : LocationFragment<FragmentDetailBinding>() {

    private val detailViewModel by viewModels<DetailViewModel> { ViewModelFactory(this) }
    private val adapter = HourRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ) = FragmentDetailBinding.inflate(layoutInflater, container, false)

    override fun onLocationRequestAction(location: Location) {
        detailViewModel.requestHourlyWeather(latitude = location.latitude,
            longitude = location.longitude)
    }

    private fun initViews() {
        binding.hourList.adapter = adapter
        binding.hourList.layoutManager = LinearLayoutManager(context)
    }

    private fun observeLiveData() {
        detailViewModel.dailyWeatherLiveData.observe(viewLifecycleOwner) { displayList ->
            val list = displayList ?: return@observe
            adapter.hourlyWeatherDisplayList = list
        }

        detailViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object {
        const val TAG = "DetailFragment"
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }
}
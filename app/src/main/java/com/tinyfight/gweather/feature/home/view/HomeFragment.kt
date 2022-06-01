package com.tinyfight.gweather.feature.home.view

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.base.LocationFragment
import com.tinyfight.gweather.common.viewmodel.ViewModelFactory
import com.tinyfight.gweather.databinding.FragmentHomeBinding
import com.tinyfight.gweather.feature.detail.view.DetailActivity
import com.tinyfight.gweather.feature.home.viewmodel.HomeViewModel
import com.tinyfight.gweather.feature.home.widget.DayRecyclerViewAdapter
import com.tinyfight.gweather.feature.util.mapIconToDrawable

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.view.HomeFragment
 */
class HomeFragment : LocationFragment<FragmentHomeBinding>() {
    private val mainViewModel by viewModels<HomeViewModel> { ViewModelFactory(this) }
    private val adapter = DayRecyclerViewAdapter()

    override fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.dayList.isNestedScrollingEnabled = false
        binding.dayList.adapter = adapter
        binding.dayList.layoutManager = LinearLayoutManager(context)
        binding.currentContainer.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java))
        }
    }

    override fun onLocationRequestAction(location: Location) {
        mainViewModel.getWeatherByLocation(location.latitude, location.latitude)
    }

    private fun observeLiveData() {
        mainViewModel.dailyWeatherLiveData.observe(viewLifecycleOwner) { homeDisplayVO ->
            val displayVO = homeDisplayVO ?: return@observe
            binding.currentDate.text = displayVO.currentWeatherDisplayVO.day
            binding.currentTemperature.text =
                displayVO.currentWeatherDisplayVO.currentTemperature.toString()
            binding.highestTemp.text = getString(R.string.high_temperature,
                displayVO.currentWeatherDisplayVO.highTemperature.toString())
            binding.lowestTemp.text = getString(R.string.low_temperature,
                displayVO.currentWeatherDisplayVO.lowTemperature.toString())
            binding.weatherIcon.setImageResource(mapIconToDrawable(displayVO.currentWeatherDisplayVO.icon))

            adapter.dailyWeatherDisplayList = displayVO.dailyWeatherDisplayVOList
        }

        mainViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
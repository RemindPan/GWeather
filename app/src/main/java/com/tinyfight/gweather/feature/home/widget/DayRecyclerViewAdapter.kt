package com.tinyfight.gweather.feature.home.widget

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.base.ViewBindingViewHolder
import com.tinyfight.gweather.common.base.getViewHolder
import com.tinyfight.gweather.data.model.DailyWeatherDisplayVO
import com.tinyfight.gweather.databinding.ItemDayWeatherBinding
import com.tinyfight.gweather.feature.util.getString
import com.tinyfight.gweather.feature.util.mapIconToDrawable

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.widget.DayRecyclerView
 */
@SuppressLint("NotifyDataSetChanged")
class DayRecyclerViewAdapter :
    RecyclerView.Adapter<ViewBindingViewHolder<ItemDayWeatherBinding>>() {
    var dailyWeatherList: List<DailyWeatherDisplayVO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewBindingViewHolder<ItemDayWeatherBinding> {
        return parent.getViewHolder(ItemDayWeatherBinding::inflate)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<ItemDayWeatherBinding>,
        position: Int,
    ) {
        if (position in 0..itemCount) {
            val item = dailyWeatherList[position]
            holder.binding.day.text = item.day
            holder.binding.highTemp.text =
                getString(R.string.high_temperature, item.highTemperature.toString())
            holder.binding.lowTemp.text =
                getString(R.string.low_temperature, item.highTemperature.toString())
            holder.binding.weatherIcon.setImageResource(mapIconToDrawable(item.icon))
        }

    }

    override fun getItemCount() = dailyWeatherList.size
}
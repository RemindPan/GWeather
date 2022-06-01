package com.tinyfight.gweather.feature.detail.widget

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinyfight.gweather.common.base.ViewBindingViewHolder
import com.tinyfight.gweather.common.base.getViewHolder
import com.tinyfight.gweather.data.displaymodel.HourlyWeatherDisplayVO
import com.tinyfight.gweather.databinding.ItemHourWeatherBinding
import com.tinyfight.gweather.feature.util.mapIconToDrawable

/**
 * Create at 2022/6/1
 * @author Yao
 * Name com.tinyfight.gweather.feature.detail.widget.HourRecyclerViewAdapter
 */
@SuppressLint("NotifyDataSetChanged")
class HourRecyclerViewAdapter : RecyclerView.Adapter<ViewBindingViewHolder<ItemHourWeatherBinding>>() {
    var hourlyWeatherDisplayList: List<HourlyWeatherDisplayVO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewBindingViewHolder<ItemHourWeatherBinding> {
        return parent.getViewHolder(ItemHourWeatherBinding::inflate)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<ItemHourWeatherBinding>,
        position: Int,
    ) {
        if (position in 0..itemCount) {
            val item = hourlyWeatherDisplayList[position]
            holder.binding.hour.text = item.hour.toString()
            holder.binding.weatherIcon.setImageResource(mapIconToDrawable(item.icon))
            holder.binding.temperature.text = item.temperature.toString()
        }
    }

    override fun getItemCount() = hourlyWeatherDisplayList.size
}
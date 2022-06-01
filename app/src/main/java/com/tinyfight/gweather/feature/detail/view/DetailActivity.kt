package com.tinyfight.gweather.feature.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.base.ViewBindingActivity
import com.tinyfight.gweather.databinding.ActivityDetailBinding

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.feature.detail.view.DetailActivity
 */

class DetailActivity : ViewBindingActivity<ActivityDetailBinding>() {
    override fun bindingInflate(layoutInflater: LayoutInflater) =
        ActivityDetailBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContent, DetailFragment.newInstance(), DetailFragment.TAG)
                .commit()
        }
    }
}
package com.tinyfight.gweather.feature.home.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.base.ViewBindingActivity
import com.tinyfight.gweather.databinding.ActivityHomeBinding

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.feature.home.view.HomeActivity
 */

class HomeActivity : ViewBindingActivity<ActivityHomeBinding>() {
    override fun bindingInflate(layoutInflater: LayoutInflater) =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContent, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit()
        }
    }
}
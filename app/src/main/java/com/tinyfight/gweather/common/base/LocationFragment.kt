package com.tinyfight.gweather.common.base

import android.Manifest
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewbinding.ViewBinding
import com.tinyfight.gweather.GWeatherApplication
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.location.LocationSupport
import com.tinyfight.gweather.common.location.OneTimeLocationListener
import com.tinyfight.gweather.common.location.hasLocationPermission
import com.tinyfight.gweather.common.location.isGPSOpen
import com.tinyfight.gweather.common.widget.LocationDialogFragment

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.base.LocationFragment
 */
abstract class LocationFragment<VB : ViewBinding> : ViewBindingFragment<VB>() {

    private val locationListener = OneTimeLocationListener(::onLocationRequested)
    private var firstStart = true

    private val requestLocationPermissionRegister =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            onRequestLocationPermissionCallback(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocation()
    }

    override fun onStart() {
        super.onStart()
        if (!firstStart) {
            requestLocation()
        }
        firstStart = false
    }

    override fun onPause() {
        super.onPause()
        LocationSupport.instance.removeListener(locationListener)
    }

    private fun requestLocation() {
        checkGPSStatus()
    }

    private fun requestPermission() {
        if (hasLocationPermission()) {
            onLocationPermissionGranted()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkGPSStatus() {
        if (isGPSOpen()) {
            requestPermission()
        } else {
            showGPSOpenDialog()
        }
    }

    private fun onRequestLocationPermissionCallback(result: Boolean) {
        if (result) {
            onLocationPermissionGranted()
        } else {
            showPermissionDialog()
        }
    }

    private fun startGPSSettingAction() {
        val intent = Intent().apply {
            action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
        }
        startActivity(intent)
    }

    private fun requestLocationPermission() {
        requestLocationPermissionRegister.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun gotoAppDetailSetting() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.parse("package:${GWeatherApplication.application.packageName}")
        }
        startActivity(intent)
    }

    private fun showGPSOpenDialog() {
        LocationDialogFragment.Builder.newBuilder().title(getString(R.string.gps_error_title))
            .subTitle(getString(R.string.gps_error_sub_title)).confirmCallback {
                startGPSSettingAction()
            }.build().show(childFragmentManager, LocationDialogFragment.TAG)
    }

    private fun showPermissionDialog() {
        LocationDialogFragment.Builder.newBuilder()
            .title(getString(R.string.location_permission_error_title))
            .subTitle(getString(R.string.location_permission_error_sub_title)).confirmCallback {
                gotoAppDetailSetting()
            }.build().show(childFragmentManager, LocationDialogFragment.TAG)
    }

    private fun onLocationRequested(location: Location) {
        onLocationRequestAction(location)
    }

    private fun onLocationPermissionGranted() {
        LocationSupport.instance.getLocation(locationListener)
    }

    abstract fun onLocationRequestAction(location: Location)
}
package com.tinyfight.gweather.common.base

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewbinding.ViewBinding
import com.tinyfight.gweather.R
import com.tinyfight.gweather.common.location.hasLocationPermission
import com.tinyfight.gweather.common.location.isGPSOpen
import com.tinyfight.gweather.common.widget.LocationDialogFragment

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.base.LocationFragment
 */
abstract class LocationFragment<VB : ViewBinding> : ViewBindingFragment<VB>() {

    private val openGpsRegister =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onGPSSettingCallback(it.resultCode)
        }

    private val requestLocationPermissionRegister =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            onRequestLocationPermissionCallback(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocation()
    }

    protected fun requestLocation() {
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

    private fun onGPSSettingCallback(result: Int) {
        requestPermission()
//        if (result == Activity.RESULT_OK) {
//            requestPermission()
//        }
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
        openGpsRegister.launch(intent)
    }

    private fun requestLocationPermission() {
        requestLocationPermissionRegister.launch(Manifest.permission.ACCESS_FINE_LOCATION)
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
                requestLocationPermission()
            }.build().show(childFragmentManager, LocationDialogFragment.TAG)
    }

    abstract fun onLocationPermissionGranted()
}
package com.tinyfight.gweather.common.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.tinyfight.gweather.GWeatherApplication

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.PermissionSuppportUtil
 */

fun isGPSOpen(): Boolean {
    val locationManager =
        GWeatherApplication.application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

fun Fragment.hasLocationPermission(): Boolean {
    val ctx = context ?: return false
    return if (isAdded) {
        ContextCompat.checkSelfPermission(ctx,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    } else {
        false
    }
}

package com.tinyfight.gweather.common.location

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.tinyfight.gweather.GWeatherApplication
import com.tinyfight.gweather.common.Callback1


/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.LocationSupportUtil
 */

class LocationSupport private constructor() {
    private val locationManager = GWeatherApplication.application
        .getSystemService(LOCATION_SERVICE) as LocationManager

    private val oneTimeLocationListener = OneTimeLocationListener(locationManager)

    @SuppressLint("MissingPermission")
    fun getLocation(callback1: Callback1<Location>) {
        val criteria = Criteria().apply {
            accuracy = Criteria.ACCURACY_FINE
            powerRequirement = Criteria.POWER_LOW
            isAltitudeRequired = false
            isBearingRequired = false
        }

        val provider = locationManager.getBestProvider(criteria, true) ?: return

        val oldLocation = locationManager.getLastKnownLocation(provider)
        oldLocation?.let {
            callback1.invoke(it)
        }

        // This way is too slow to get, should better replace with third part map lib
        oneTimeLocationListener.setCallback(callback1)
        locationManager.requestLocationUpdates(provider,
            1000,
            1F,
            oneTimeLocationListener)
    }

    fun onPagePause() {
        locationManager.removeUpdates(oneTimeLocationListener)
    }

    companion object {
        val instance by lazy {
            LocationSupport()
        }
    }
}

class OneTimeLocationListener(
    private val locationManager: LocationManager,
) : LocationListener {
    private var callback1: Callback1<Location>? = null

    fun setCallback(callback1: Callback1<Location>) {
        this.callback1 = callback1
    }

    override fun onLocationChanged(location: Location) {
        callback1?.invoke(location)
        locationManager.removeUpdates(this)
    }
}

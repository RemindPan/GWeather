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

    @SuppressLint("MissingPermission")
    fun getLocation(locationListener: OneTimeLocationListener) {
        val criteria = Criteria().apply {
            accuracy = Criteria.ACCURACY_FINE
            powerRequirement = Criteria.POWER_LOW
            isAltitudeRequired = false
            isBearingRequired = false
        }

        val provider = locationManager.getBestProvider(criteria, true) ?: return

        // This way is too slow to get, should better replace with third part map lib
        locationListener.setLocationManager(locationManager)
        locationManager.requestLocationUpdates(provider,
            1000,
            1F,
            locationListener)

        val bestLocation = locationManager.getLastKnownLocation(provider)
        val fastLocation = getLastKnowLocationFast()
        if (bestLocation != null && fastLocation != null) {
            if (bestLocation.accuracy < fastLocation.accuracy) {
                locationListener.callback1.invoke(bestLocation)
            } else {
                locationListener.callback1.invoke(fastLocation)
            }
        } else if (bestLocation != null) {
            locationListener.callback1.invoke(bestLocation)
        } else if (fastLocation != null) {
            locationListener.callback1.invoke(fastLocation)
        }
    }

    fun removeListener(locationListener: OneTimeLocationListener) {
        locationManager.removeUpdates(locationListener)
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnowLocationFast(): Location? {
        var location: Location? = null
        val providers = locationManager.getProviders(true)
        for (provider in providers) {
            val lastKnowLocation = locationManager.getLastKnownLocation(provider) ?: continue
            if (location == null || lastKnowLocation.accuracy < location.accuracy) {
                location = lastKnowLocation
            }
        }
        return location
    }


    companion object {
        val instance by lazy {
            LocationSupport()
        }
    }
}

class OneTimeLocationListener(
    val callback1: Callback1<Location>,
) : LocationListener {
    private var locationManager: LocationManager? = null

    fun setLocationManager(locationManager: LocationManager) {
        this.locationManager = locationManager
    }

    override fun onLocationChanged(location: Location) {
        callback1.invoke(location)
        locationManager?.removeUpdates(this)
    }
}

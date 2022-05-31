package com.tinyfight.gweather

import android.app.Application
import com.tinyfight.gweather.common.NETWORK_BASE_URL
import com.tinyfight.gweather.data.network.client.NetworkClient

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.GWeatherApplication
 */
class GWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSelf(this)
        initNetwork()
    }

    private fun initNetwork() {
        NetworkClient.initialize(
            NetworkClient.Builder().setBaseUrl(NETWORK_BASE_URL).build()
        )
    }

    companion object {
        lateinit var application: Application

        fun initializeSelf(app: Application) {
            application = app
        }
    }
}
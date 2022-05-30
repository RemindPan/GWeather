package com.tinyfight.gweather.data.network.header

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.network.header
 */
class NetworkHeaderInterceptor(private val header: NetworkHeader) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val request = chain.request()
            .newBuilder()
            .headers(header.get().toHeaders())
            .build()
        proceed(request)
    }
}
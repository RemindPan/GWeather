package com.tinyfight.gweather.data.network.header

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.data.network.header.NetworkHeader
 */
interface NetworkHeader {
    fun get(): Map<String, String>
}
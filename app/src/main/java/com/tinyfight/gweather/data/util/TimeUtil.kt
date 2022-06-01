package com.tinyfight.gweather.data.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.data.TimeUtil
 */
fun Long.timeStampToDate(): String {
    val ms = this * 1000L
    return SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(ms)
}

fun Long.timeStampToMinute(): String {
    val ms = this * 1000L
    return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(ms)
}

fun Long.timeStampGetHour(): Int {
    val ms = this * 1000L
    val calender = Calendar.getInstance()
    calender.time = Date(ms)
    return calender.get(Calendar.HOUR_OF_DAY)
}

package com.mecitdeniz.bitcointicker.common

object Utils {
    fun getIntervalString(value: Long): String {
        return Constants.REFRESH_INTERVAL.find {
            it.second == value
        }?.first ?: Constants.REFRESH_INTERVAL.first().first
    }

    fun getIntervalLong(value: String): Long {
        return Constants.REFRESH_INTERVAL.find {
            it.first == value
        }?.second ?: Constants.REFRESH_INTERVAL.first().second
    }
}
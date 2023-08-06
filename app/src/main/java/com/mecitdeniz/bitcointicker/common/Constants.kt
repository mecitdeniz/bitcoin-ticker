package com.mecitdeniz.bitcointicker.common

object Constants {
    const val BASE_URL = "https://api.coingecko.com/api/"
    const val PARAM_COIN_ID = "coinId"
    const val SHARED_PREFERENCES = "sharedPreferences"
    const val INTERVAL_PREF = "interval_pref"
    const val MY_COINS_REF = "my_coins"

    val REFRESH_INTERVAL = listOf(
        Pair("30s", 30000L),
        Pair("45s", 45000L),
        Pair("60s", 60000L),
        Pair("90s", 90000L),
        Pair("120s", 120000L),
    )
}
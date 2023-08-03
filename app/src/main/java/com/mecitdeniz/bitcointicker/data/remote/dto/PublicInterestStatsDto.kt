package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class PublicInterestStats (
    @SerializedName("alexa_rank")
    val alexaRank: Long,

    @SerializedName("bing_matches")
    val bingMatches: JsonElement? = null
)

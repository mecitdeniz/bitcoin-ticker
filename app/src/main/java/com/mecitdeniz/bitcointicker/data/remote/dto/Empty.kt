package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class Empty (
    @SerializedName("decimal_place")
    val decimalPlace: JsonElement? = null,

    @SerializedName("contract_address")
    val contractAddress: String
)

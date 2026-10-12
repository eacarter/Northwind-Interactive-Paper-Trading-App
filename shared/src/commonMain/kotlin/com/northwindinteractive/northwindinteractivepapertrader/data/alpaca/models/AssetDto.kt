package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.Serializable

@Serializable
data class AssetDto(
    var name: String,
    var symbol: String,
    var tradable : Boolean,

)

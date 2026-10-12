package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchListItemDto(
    val id :String,
    val name: String,
    val assets : List<AssetDto>,

    @SerialName("account_id")
    val accountId : String
)

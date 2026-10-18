package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchListItemDto(
    val id : String,
    val name : String,
    @SerialName("account_id")
    val accountId: String,
    @SerialName("created_at")
    val createdDate: String
)
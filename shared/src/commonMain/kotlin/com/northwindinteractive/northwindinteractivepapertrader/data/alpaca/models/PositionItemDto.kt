package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionItemDto(
    @SerialName("asset_id")
    val assetId: String,

    @SerialName("avg_entry_price")
    val avgEntryPrice: String,

    @SerialName("change_today")
    val changeToday: String,

    @SerialName("qty")
    val quantity: String,

    @SerialName("symbol")
    val symbol: String,

    @SerialName("unrealized_pl")
    val pnl: String,

    @SerialName("unrealized_plpc")
    val pnlnc: String
)

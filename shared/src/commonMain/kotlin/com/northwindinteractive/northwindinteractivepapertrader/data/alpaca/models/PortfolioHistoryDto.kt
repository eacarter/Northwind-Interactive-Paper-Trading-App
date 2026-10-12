package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioHistoryDto(
    val timestamp: List<Long>,
    val equity: List<Double?>,

    @SerialName("profit_loss")
    val profitLoss: List<Double?>,

    @SerialName("profit_loss_pct")
    val profitLossPercent: List<Double?>,

    @SerialName("base_value")
    val baseValue: Double? = null,

    @SerialName("base_value_asof")
    val baseValueAsOf: String? = null
)

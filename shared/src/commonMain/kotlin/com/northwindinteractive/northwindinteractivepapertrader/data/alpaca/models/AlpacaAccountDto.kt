package com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlpacaAccountDto(
    val id: String,
    val status: String,
    val currency: String,

    @SerialName("cash")
    val cash: String,

    @SerialName("portfolio_value")
    val portfolioValue: String,

    @SerialName("buying_power")
    val buyingPower: String,

    @SerialName("equity")
    val equity: String
)

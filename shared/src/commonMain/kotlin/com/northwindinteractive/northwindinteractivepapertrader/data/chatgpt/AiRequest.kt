package com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class AiRequest(
    val message : String,
    val context: AiContext? = null
)

@kotlinx.serialization.Serializable
data class AiContext(
    val symbol: String? = null,
    val screen: String? = null,
    val portfolioValue: String? = null
)

@Serializable
data class AiResponse(
    val message: String
)

@kotlinx.serialization.Serializable
data class ChatMessage(
    val text: String,
    val isUser: Boolean,
)
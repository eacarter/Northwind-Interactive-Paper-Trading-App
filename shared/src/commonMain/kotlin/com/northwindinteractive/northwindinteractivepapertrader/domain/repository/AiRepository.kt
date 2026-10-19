package com.northwindinteractive.northwindinteractivepapertrader.domain.repository

import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.AiContext
import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.AiResponse

interface AiRepository {
    suspend fun sendMessage(message: String, context: AiContext? = null): AiResponse
}
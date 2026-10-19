package com.northwindinteractive.northwindinteractivepapertrader.data

import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.AiContext
import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.AiRequest
import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.AiResponse
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AiRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AiRepositoryImpl(private val client: HttpClient) : AiRepository {
    override suspend fun sendMessage(
        message: String,
        context: AiContext?
    ): AiResponse {
        return client.post(""){
            contentType(ContentType.Application.Json)

            setBody(
                AiRequest(
                    message = message,
                    context = context
                )
            )
        }.body()
    }

}
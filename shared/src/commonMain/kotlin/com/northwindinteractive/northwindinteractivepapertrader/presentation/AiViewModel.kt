package com.northwindinteractive.northwindinteractivepapertrader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.northwindinteractive.northwindinteractivepapertrader.data.chatgpt.ChatMessage
import com.northwindinteractive.northwindinteractivepapertrader.domain.repository.AiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class AiViewModel(private val aiRepository: AiRepository): ViewModel() {
    private val _message = MutableStateFlow<List<ChatMessage>>(emptyList())

    val message = _message.asStateFlow()

    fun sendMessage(text: String){
        viewModelScope.launch {
            _message.update {
                it + ChatMessage(
                    text = text,
                    isUser = true
                )
            }
            try{
                val result = aiRepository.sendMessage(
                    text
                )
                _message.update {
                    it + ChatMessage(
                        text = result.message,
                        isUser = false
                    )
                }
            }catch (
                e: Exception
            ){
                //TODO: Update UI state
            }
        }
    }
}
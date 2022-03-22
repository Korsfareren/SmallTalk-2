package com.example.smalltalk.viewmodels

import androidx.lifecycle.ViewModel
import com.example.smalltalk.database.ChatMessage
import java.util.*

class MainViewModel : ViewModel() {

    val loggedInUser = "Thomas"

    fun sentMessages(): MutableList<ChatMessage> {
        val sentMessages = mutableListOf(
            ChatMessage(message = "Hei.", date = Date().toString(), sender = "Thomas"),
            ChatMessage(message = "Hei du..", date = Date().toString(), sender = "David"),
            ChatMessage(message = "Skjera", date = Date().toString(), sender = "Thomas"),
            ChatMessage(message = "Ingenting", date = Date().toString(), sender = "David"),
        )
        return sentMessages
    }
}
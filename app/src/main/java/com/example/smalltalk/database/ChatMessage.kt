package com.example.smalltalk.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String,
    val date: String,
    val sender: String
)
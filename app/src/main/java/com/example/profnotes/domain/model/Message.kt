package com.example.profnotes.domain.model

import androidx.room.PrimaryKey

class Message(
    @PrimaryKey
    val id: String,
    val author: User,
    val text: String,
    val attachments: Array<String>,
    val status: String
)
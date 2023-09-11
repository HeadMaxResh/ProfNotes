package com.example.profnotes.domain.model

class Chat(
    val users: Array<User>,
    val image: String,
    val messages: Array<Message>,
    val id: String,
    val name: String
)
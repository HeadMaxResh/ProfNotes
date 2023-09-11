package com.example.profnotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommunityNote(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: Array<RichText>,
    val author: User,
    val date: Long,
    val comments: Array<Message>?,
)
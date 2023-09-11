package com.example.profnotes.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profnotes.data.database.LOCAL_NOTES_TABLE
import com.example.profnotes.domain.model.Message
import com.example.profnotes.domain.model.RichText

@Entity(tableName = LOCAL_NOTES_TABLE)
class LocalNoteEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: Array<RichText>,
    val date: Long,
    val comments: Array<Message>?,
    var isBookmarked: Boolean = false
)
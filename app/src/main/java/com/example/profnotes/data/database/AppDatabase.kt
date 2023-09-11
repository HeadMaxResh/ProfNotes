package com.example.profnotes.data.database

import androidx.room.Database;
import androidx.room.RoomDatabase
import com.example.profnotes.data.database.dao.BookmarkDao
import com.example.profnotes.data.database.dao.LocalNoteDao
import com.example.profnotes.data.database.model.LocalNoteEntity

@Database(
    version = 1,
    entities = [
        LocalNoteEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBookmarkDao(): BookmarkDao
    abstract fun getLocalNoteDao(): LocalNoteDao

}
package com.example.profnotes.data.database

import android.content.Context
import androidx.room.Room

const val BOOKMARKED_COURSES_TABLE = "BOOKMARKED_COURSES_TABLE"
const val BOOKMARKED_GLOBAL_NOTES_TABLE = "BOOKMARKED_GLOBAL_NOTES_TABLE"
const val LOCAL_NOTES_TABLE = "LOCAL_NOTES_TABLE"

object Database {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").build()
    }

    val localNoteDao by lazy { appDatabase.getLocalNoteDao() }
    val bookmarkDao by lazy { appDatabase.getBookmarkDao() }
}
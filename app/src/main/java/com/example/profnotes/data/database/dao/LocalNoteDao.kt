package com.example.profnotes.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.profnotes.data.database.model.LocalNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalNoteDao {
    @Query("SELECT * FROM local_note")
    suspend fun getLocalNotes(): List<LocalNoteEntity>

    @Query("SELECT * FROM local_note WHERE id = :id")
    suspend fun getLocalNoteById(): LocalNoteEntity

    @Query("SELECT * FROM local_note LIMIT 1")
    fun getFirstLocalNote(): Flow<LocalNoteEntity>

    @Insert
    suspend fun insert(note: LocalNoteEntity)

    @Delete
    suspend fun delete(note: LocalNoteEntity)
}
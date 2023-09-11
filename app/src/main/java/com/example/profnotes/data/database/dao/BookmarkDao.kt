package com.example.profnotes.data.database.dao

import androidx.room.*
import com.example.profnotes.data.database.BOOKMARKED_COURSES_TABLE
import com.example.profnotes.data.database.BOOKMARKED_GLOBAL_NOTES_TABLE
import com.example.profnotes.data.database.LOCAL_NOTES_TABLE
import com.example.profnotes.data.database.model.BookmarkedCoursesEntity
import com.example.profnotes.data.database.model.BookmarkedGlobalNotesEntity
import com.example.profnotes.data.database.model.LocalNoteEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkDao {
    @Update
    suspend fun updateBookmarkLocalNote(note: LocalNoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkGlobalNote(note: BookmarkedGlobalNotesEntity)

    @Delete
    suspend fun unbookmarkGlobalNote(note: BookmarkedGlobalNotesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCourse(course: BookmarkedCoursesEntity)

    @Delete
    suspend fun unbookmarkCourse(course: BookmarkedCoursesEntity)

    @Query("SELECT COUNT(id) FROM $BOOKMARKED_GLOBAL_NOTES_TABLE WHERE id = :id")
    fun isGlobalNoteBookmarked(id: String): Flow<Int>

    @Query("SELECT COUNT(id) FROM $BOOKMARKED_COURSES_TABLE WHERE id = :id")
    fun isCourseBookmarked(id: String): Flow<Int>

    @Query("SELECT COUNT(id) FROM $LOCAL_NOTES_TABLE WHERE id = :id AND isBookmarked = 1")
    fun isLocalNoteBookmarked(id: Int): Flow<Int>

    @Query("SELECT * FROM $LOCAL_NOTES_TABLE WHERE isBookmarked = 1")
    fun getBookmarkedLocalNotes(): Flow<List<LocalNoteEntity>?>

    @Query("SELECT * FROM $BOOKMARKED_COURSES_TABLE")
    fun getBookmarkedCourses(): Flow<List<BookmarkedCoursesEntity>?>

    @Query("SELECT * FROM $BOOKMARKED_GLOBAL_NOTES_TABLE")
    fun getBookmarkedGlobalNotes(): Flow<List<BookmarkedGlobalNotesEntity>?>

    @Query("SELECT * FROM $LOCAL_NOTES_TABLE WHERE isBookmarked = 1 LIMIT 1")
    fun getFirstBookmarkedLocalNote(): Flow<LocalNoteEntity?>

    @Query("SELECT * FROM $BOOKMARKED_GLOBAL_NOTES_TABLE LIMIT 1")
    fun getFirstBookmarkedGlobalNote(): Flow<BookmarkedGlobalNotesEntity?>
}
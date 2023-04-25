package com.example.notes_project

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)//replace - если пришли те же данные
    suspend fun insert(note: Note)

    @Delete(entity = Note::class)
    suspend fun delete(note: Note)
}
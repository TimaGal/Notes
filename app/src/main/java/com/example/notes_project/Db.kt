package com.example.notes_project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class Db: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
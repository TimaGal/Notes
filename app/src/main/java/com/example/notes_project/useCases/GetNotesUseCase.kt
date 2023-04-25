package com.example.notes_project.useCases

import com.example.notes_project.Note
import com.example.notes_project.NotesDao
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase {
    fun execute(notesDao: NotesDao): Flow<List<Note>>{
        return notesDao.getAllNotes()
    }
}
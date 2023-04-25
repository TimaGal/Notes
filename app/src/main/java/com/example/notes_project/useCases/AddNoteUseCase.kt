package com.example.notes_project.useCases

import com.example.notes_project.Note
import com.example.notes_project.NotesDao

class AddNoteUseCase() {
    suspend fun execute(notesDao: NotesDao, note: Note){
        notesDao.insert(note)
    }
}
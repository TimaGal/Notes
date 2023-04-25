package com.example.notes_project.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_project.*
import com.example.notes_project.useCases.AddNoteUseCase
import com.example.notes_project.useCases.GetNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel(ctx: Context): ViewModel() {

    @Inject
    lateinit var dao: NotesDao
    @Inject
    lateinit var getNotesUseCase: GetNotesUseCase
    @Inject
    lateinit var addNoteUseCase: AddNoteUseCase
    init {
        var appComponent2 = DaggerAppComponent2.builder().context(ctx).build()
        appComponent2.inject(this)
        getNotes()
    }

    private val _msg1 = MutableSharedFlow<List<String>>(replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val msg1 = _msg1.asSharedFlow()
    fun getNotes(){
        val startTime = System.currentTimeMillis()
        viewModelScope.launch {
            getNotesUseCase.execute(dao).
                flatMapMerge{
                    editNotesFlow(it)
                }.
            flowOn(Dispatchers.IO).collectLatest {
                println(" Time is ${System.currentTimeMillis() - startTime}")
                _msg1.tryEmit(it)
            }
        }
    }

    fun editNotesFlow(values: List<Note>): Flow<List<String>> = flow{
        var strs = arrayListOf<String>()
        for(i in values){
            strs.add(i.body)
        }
        emit(strs)
    }
    //viewModelScope.launch {
    //    msg1.emitAll(flow{
    //        emit(useCase())
    //    })
    //}
//
    fun insertNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.execute(dao, note)
        }
    }
}
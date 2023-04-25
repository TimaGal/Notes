package com.example.notes_project

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_project.presentation.Body
import com.example.notes_project.presentation.BodyActivityViewModel
import com.example.notes_project.presentation.MainActivity
import com.example.notes_project.presentation.MainActivityViewModel
import com.example.notes_project.useCases.AddNoteUseCase
import com.example.notes_project.useCases.DeleteNoteUseCase
import com.example.notes_project.useCases.GetNotesUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesViewModelInstance(mainActivity: MainActivity, ctx: Context): MainActivityViewModel{
        return ViewModelProvider(mainActivity, MainFactory(ctx)).get(MainActivityViewModel::class.java)
    }

}

@Module
class AppModule2{
    @Provides
    fun providesDataBaseInstanceDao(ctx: Context): NotesDao{
        return Room.databaseBuilder(
            ctx,
            Db::class.java, "db-name"
        ).build().notesDao()
    }

    @Provides
    fun providesAddNoteUseCase(): AddNoteUseCase{
        return AddNoteUseCase()
    }

    @Provides
    fun provideGetNotesUseCase(): GetNotesUseCase{
        return GetNotesUseCase()
    }

    @Provides
    fun providesDeleteNoteUseCase(): DeleteNoteUseCase{
        return DeleteNoteUseCase()
    }
}

@Module
class AppModule3{
    @Provides
    fun provideBodyViewModelInstance(bodyActivity: Body) : BodyActivityViewModel{
        return ViewModelProvider(bodyActivity).get(BodyActivityViewModel::class.java)
    }

    @Provides
    fun provideSelectedColorsState(): BooleanArray = booleanArrayOf(true, false, false, false, false, false, false, false, false, false)
}



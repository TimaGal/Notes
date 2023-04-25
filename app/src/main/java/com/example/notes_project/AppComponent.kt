package com.example.notes_project

import android.app.Application
import android.content.Context
import com.example.notes_project.presentation.Body
import com.example.notes_project.presentation.BodyActivityViewModel
import com.example.notes_project.presentation.MainActivity
import com.example.notes_project.presentation.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@Component (modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{
        fun build(): AppComponent

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder

        @BindsInstance
        fun context(ctx: Context): Builder

        @BindsInstance
        fun app(application: Application): Builder
    }

}

@Component (modules = [AppModule2::class])
interface AppComponent2 {
    fun inject(mainActivityViewModel: MainActivityViewModel)

    @Component.Builder
    interface Builder{
        fun build(): AppComponent2

        @BindsInstance
        fun context(context: Context): Builder

    }

}
@Component(modules = [AppModule3::class])
interface AppBodyComponent{
    fun inject(bodyActivity: Body)

    @Component.Builder
    interface Builder{
        fun build(): AppBodyComponent

        @BindsInstance
        fun bodyActivity(bodyActivity: Body): Builder
    }
}



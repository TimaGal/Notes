package com.example.notes_project

import android.app.Application

class MyApp: Application(){
    //var appComponent2: AppComponent2? = null
    override fun onCreate() {
        super.onCreate()
        //appComponent2 = DaggerAppComponent2.builder().context(applicationContext).build()
    }
}
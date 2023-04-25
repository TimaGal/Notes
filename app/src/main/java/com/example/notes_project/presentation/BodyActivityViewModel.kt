package com.example.notes_project.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes_project.R

class BodyActivityViewModel(app: Application): AndroidViewModel(app) {
    var colorState: MutableLiveData<MutableList<Boolean>>
    var styleState: MutableLiveData<String>

    init {
        colorState = MutableLiveData(mutableListOf(true, false, false, false, false, false, false, false, false, false))
        styleState = MutableLiveData(app.resources.getString(R.string.normal))
    }

    fun setChoosedColorState(li: MutableList<Boolean>){
        colorState.postValue(li)
    }

    fun setTextStyle(styleKey: String){
        styleState.postValue(styleKey)
    }
}

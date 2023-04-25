package com.example.notes_project.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//здесь будет получение данных
class MyViewModel: ViewModel() {
    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0){
            delay(1000L)
            currentValue--
            //notify our ui
            emit(currentValue)
        }
    }
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()
    private val _sharedFlow = MutableSharedFlow<Int>(replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val shredFlow = _sharedFlow.asSharedFlow()
    init {
        //collectFlow()
        squareNumber(3)
        viewModelScope.launch {
            _sharedFlow.collect{
                println("The got number is $it")
                delay(500)
            }
        }
        viewModelScope.launch {
            _sharedFlow.collect{
                println("The got number is $it")
                delay(1000)
            }
        }
    }


    fun incrementCounter(){
        _stateFlow.value += 1

    }

    private fun collectFlow(){
        viewModelScope.launch(Dispatchers.IO){
            countDownFlow.filter {time->
                time%2 == 0
            }.map{
                time-> time*time
            }.onEach {

            }
                .collect{time->
                println("The cur time is $time")
            }
        }
    }


    fun squareNumber(number: Int){
        viewModelScope.launch {
            _sharedFlow.emit(number*number)
        }
    }
}
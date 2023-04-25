package com.example.notes_project.presentation

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(useCase: ObserveMessage): ViewModel() {
    private val _message  = MutableStateFlow("Android brodcast")
    val message: StateFlow<String> = _message.asStateFlow()//stateFlow доступен только для чтения

    private val _meass_visible = MutableStateFlow(true)
    val mess_visible: StateFlow<Boolean> = _meass_visible

    //корутины не знают ничего про lifecycle, они работают через coroutine scope

    //со stateFlow можно убрать требование на MainThread, так как stateFlow является потокобезопасным и задавать иуда значения можно с любого потока

    @MainThread
    fun setMessageVisible(vis: Boolean){
        _meass_visible.value = vis
    }

    //state in - 1)suspend-функция 2)
   //val mess: StateFlow<String> = useCase().stateIn(viewModelScope, started = SharingStarted.Lazily, "Android broadcast")

    //val mess1: SharedFlow<String> = useCase().shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 0)
    //replay = 0 значит, сколько значений будет доставляться новым подписчикам, 0 - значит всё каждый раз будет доставляться заново
    //replay = 1, то поведение будет аналогично  liveData

    //sharedFlow не требует начального значения
    //SharingStarted.Lazily - начнётся, когда появится первый подписчик

    //следующий кусок кода - для случая, если мы можем изменить это сообщение извне
    private val msg1 = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val message1 = msg1.asSharedFlow()
    init {
        viewModelScope.launch {
            msg1.emitAll(flow{
                emit(useCase())
            })
        }
    }
    fun setMessage(str: String){
        msg1.tryEmit(str)
    }

}
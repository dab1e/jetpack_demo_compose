package com.example.jetpack_compose_demo.view

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_demo.repo.MyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _eventFlow = MutableSharedFlow<UIState>()
    val eventFlow = _eventFlow.asSharedFlow()
    var username = mutableStateOf("")
    var pasword = mutableStateOf("")
    var textStatus = mutableStateOf("")
    val repo = MyRepository()

    fun getListImage(): List<String> {
        return repo.getList()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.Login -> {
                Log.d(javaClass.name, "${username.value} - ${pasword.value}")
                  viewModelScope.launch {
                      if(username.value == "" && pasword.value == "") {
                          _eventFlow.emit(UIState.NavigateToHome)
                          textStatus.value = "Login success"
                      } else {
                          _eventFlow.emit(UIState.LoginFailure)
                          textStatus.value = "Login failure!"
                      }
                  }
            }
            is UIEvent.Register -> {
                viewModelScope.launch {
                    _eventFlow.emit(UIState.Register)
                }
            }
        }
    }

    sealed class UIEvent {
        object Login : UIEvent()
        object Register : UIEvent()
    }

    sealed class UIState {
        object NavigateToHome : UIState()
        object ShowLoading : UIState()
        object LoginFailure: UIState()
        object Register: UIState()
    }

}
package com.example.jetpack_compose_demo.view

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class LoginViewModel: ViewModel() {

    var stateLogin = mutableStateOf(LoginState.NONE)
        private set

    suspend fun login(userName: String, passWord: String) {
        coroutineScope {
            Log.e("VIEWMODEL", stateLogin.value.toString())
            stateLogin.value = LoginState.START
            Log.e("VIEWMODEL", stateLogin.value.toString())
            stateLogin.value = LoginState.LOADING
            Log.e("VIEWMODEL", stateLogin.value.toString())
            delay(1500L)
            if(userName == "" && passWord == ""){
                stateLogin.value = LoginState.SUCCESS
                Log.e("VIEWMODEL", stateLogin.value.toString())
            }else {
                stateLogin.value = LoginState.FAILURE
                Log.e("VIEWMODEL", stateLogin.value.toString())
            }
        }
    }


    fun loginfLow(userName: String, passWord: String) = flow{
        emit(LoginState.START)
        emit(LoginState.LOADING)
        if(userName == "" && passWord == ""){
            emit(LoginState.SUCCESS)
        }else {
            emit(LoginState.FAILURE)
        }
        awaitCancellation()
    }

    fun getUser(){
        runBlocking {

        }

    }
}
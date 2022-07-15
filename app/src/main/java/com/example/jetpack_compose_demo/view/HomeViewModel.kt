package com.example.jetpack_compose_demo.view

import android.util.Log
import androidx.compose.runtime.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class HomeViewModel  {

    val text = MutableStateFlow("")

    var stateLogin = mutableStateOf(LoginState.NONE)

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

class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit
) : MutableState<T> {

    override var value: T
        get() = state.value
        set(value) {
            mutate(value)
        }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = { value = it }
}

@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(
    context: CoroutineContext = EmptyCoroutineContext
): MutableState<T> = MutableStateAdapter(
    state = collectAsState(context),
    mutate = { value = it }
)

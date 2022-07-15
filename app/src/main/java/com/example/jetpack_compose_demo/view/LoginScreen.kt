package com.example.jetpack_compose_demo.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.ui.theme.Jetpack_compose_demoTheme
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_HOME
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_LOGIN
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_SIGN
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun ScreenLogin(navControler: NavHostController, viewmodel: HomeViewModel) {

    val userName by rememberSaveable { mutableStateOf("") }
    val passWord by rememberSaveable { mutableStateOf("") }
    var sign by rememberSaveable { mutableStateOf(false)}
    var stateLogin by rememberSaveable { mutableStateOf(LoginState.NONE)}
    var isClickLogin by rememberSaveable { mutableStateOf(false)}

    val loginStateView = LoginStateView(
        userName = "",
        passWord = "",
        loginStatus = LoginState.NONE,
        onClickSign = {sign = true},
        onClickLogin = {isClickLogin = true}
    )


    var cout by rememberSaveable { mutableStateOf(0)}
    val a = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }

    cout = a.collectAsState(initial = 0).value

    if(cout!=0){
//        Log.e("FLOWW", cout.toString())
    }

    when(stateLogin){
        LoginState.NONE ->{

            }
        LoginState.LOADING ->{

            }
        LoginState.FAILURE ->{
            isClickLogin = false
            }
        LoginState.SUCCESS ->{
            isClickLogin = false
            LaunchedEffect(true) {
                navControler.navigate(SCREEN_HOME) {
                    popUpTo(SCREEN_LOGIN) { inclusive = true }
                }
            }
        }
    }

    Log.e("V_STATE", "user: $userName __ pass: $passWord")
        var loginFlow : Flow<LoginState>? = null
        LaunchedEffect(isClickLogin){
            loginFlow = viewmodel.loginfLow(userName =  userName, passWord = passWord)
        }
       if (loginFlow != null) {
            stateLogin = loginFlow!!.collectAsState(initial = LoginState.NONE).value
            if(stateLogin == LoginState.SUCCESS || stateLogin == LoginState.FAILURE) {
                isClickLogin = false
            }
       }

//        coroutineScope.launch {
//            viewmodel.login(userName =  userName, passWord = passWord)
//            stateLogin = viewmodel.stateLogin.value
//        }

    if(stateLogin!=null){
        Log.e("V_STATE", stateLogin.toString())
    }

    if(sign){
        LaunchedEffect(true) {
            navControler.navigate(SCREEN_SIGN) {
                popUpTo(SCREEN_LOGIN) { inclusive = true }
            }
        }
    }

    Jetpack_compose_demoTheme {
        ScreenLoginPreview(loginStateView)
    }
}

enum class LoginState{NONE, START, LOADING, SUCCESS, FAILURE}

data class LoginStateView(
    var userName: String,
    var passWord: String,
    var loginStatus: LoginState,
    val onClickSign:() -> Unit,
    val onClickLogin:() -> Unit
){
    val onValueChangeUser:(String) -> Unit = {userName = it}
    val onValueChangePass:(String) -> Unit = {passWord = it}
}

class LoginScreenState(val viewmodel: HomeViewModel){
    var userName: String = ""
    var passWord: String = ""
}

@Composable
fun ScreenLoginPreview(loginStateView: LoginStateView){
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {

        Image(
            painter = painterResource(R.drawable.bg_jp_compose),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var textStatus by rememberSaveable { mutableStateOf("")}
            when(loginStateView.loginStatus){
                LoginState.NONE ->{
                    textStatus = ""
                }
                LoginState.LOADING ->{
                    textStatus = "Loading..."
                }
                LoginState.FAILURE ->{
                    textStatus = "Login falure!"
                }
                LoginState.SUCCESS ->{
                    textStatus = "Login success!"
                }
            }
            TextInput(label = "User name", onValueChange = loginStateView.onValueChangeUser, textInput = loginStateView.userName)
            TextInput(label = "Password", onValueChange = loginStateView.onValueChangePass, textInput = loginStateView.passWord)

            if(textStatus.isNotEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = textStatus, style = TextStyle(color = Color.Red),
                        modifier = Modifier.background(Color.White)
                    )
                }
            }
            MyButton(text = "Login", onClick =  loginStateView.onClickLogin)
            MyButton(text = "Sigin", onClick =  loginStateView.onClickSign)
        }
    }
}

@Composable
fun MyButton(text: String, onClick:()->Unit){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClick, modifier = Modifier.padding(10.dp)) {
            Text(text = text)
        }
    }

}

@Composable
fun TextInput(label: String, onValueChange:(String) -> Unit, textInput: String){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        OutlinedTextField(value = textInput,
            onValueChange = onValueChange,
            label = { Text(text = label)},
            modifier = Modifier.background(Color.White)
        )
    }
}

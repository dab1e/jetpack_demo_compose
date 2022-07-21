package com.example.jetpack_compose_demo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_compose_demo.R

@Composable
fun ScreenLogin(navControler: NavHostController, viewmodel: LoginViewModel) {
    LaunchedEffect(key1 = true){
        viewmodel.eventFlow.collect{
            when(it){
                is LoginViewModel.UIState.NavigateToHome ->{
                    navControler.navigate("${MainActivity.SCREEN_HOME}?userName=${viewmodel.username.value}"){
                        popUpTo(MainActivity.SCREEN_LOGIN){ inclusive = true}
                    }
                }
                is LoginViewModel.UIState.Register ->{
                    navControler.navigate(MainActivity.SCREEN_SIGN)
                }
            }
        }
    }

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

            TextInput(label = "User name", onValueChange = { viewmodel.username.value = it
            }, textInput = viewmodel.username.value)
            TextInput(label = "Password", onValueChange = {
                viewmodel.pasword.value = it
            }, textInput = viewmodel.pasword.value)

            Text(text = viewmodel.textStatus.value, style = TextStyle(color = Color.Red),
                modifier = Modifier.background(Color.White))

            MyButton(text = "Login", onClick =  {
                viewmodel.onEvent(LoginViewModel.UIEvent.Login)
            })
            MyButton(text = "Sigin", onClick =  {

            })
        }
    }

}

@Composable
fun rememberAccount(
    username: MutableState<String> = remember { mutableStateOf("") },
    password: MutableState<String> = remember { mutableStateOf("") },
) = remember {
    AccountHolder(username, password)
}

class AccountHolder(val username: MutableState<String>, val password: MutableState<String>){

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

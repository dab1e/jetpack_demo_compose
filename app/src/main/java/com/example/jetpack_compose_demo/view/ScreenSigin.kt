package com.example.jetpack_compose_demo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jetpack_compose_demo.ui.theme.Jetpack_compose_demoTheme
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_HOME
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_SIGN

@Composable
fun ScreenSign( navControler: NavHostController ) {
    Jetpack_compose_demoTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = SCREEN_SIGN)
            Button(onClick = {
                    navControler.navigate(SCREEN_HOME)
                }) {
                Text(text = "Back to screen home")
            }

            Button(onClick = {
                navControler.navigate("test?userId="){
                    launchSingleTop = true
                }
            }) {
                Text(text = "Back to screen test")
            }
        }
    }
}
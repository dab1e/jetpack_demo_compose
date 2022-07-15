package com.example.jetpack_compose_demo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_demo.ui.theme.Jetpack_compose_demoTheme

@Composable
fun ScreenHome(onClickBack:()->Unit) {
    Jetpack_compose_demoTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = MainActivity.SCREEN_HOME)
            Button(onClick = onClickBack) {
                Text(text = "Back to screen login")
            }
        }
    }

}
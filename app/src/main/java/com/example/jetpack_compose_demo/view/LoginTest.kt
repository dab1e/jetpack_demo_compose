package com.example.jetpack_compose_demo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.ui.theme.Jetpack_compose_demoTheme

@Preview
@Composable
fun MyColumn(){
    Box(modifier = Modifier
        .width(150.dp)
        .background(Color.White)) {

        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)) {

            Image(
                painter = painterResource(id = R.drawable.bg_jp_compose),
                contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .width(45.dp)
                .height(45.dp))

            Column() {
                Text(text = "Name")
                Text(text = "Age")
            }
        }

        Icon(painter = painterResource(
            id = android.R.drawable.btn_star_big_on),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

data class Data(val id: Int, val title: String, val img: Int )

@Composable
fun LazyLayout() {

    val list = arrayListOf<Data>()
    list.add(Data(1,"title 1", R.drawable.bg_jp_compose))
    list.add(Data(2,"title 2", R.drawable.bg_jp_compose))
    list.add(Data(3,"title 3", R.drawable.bg_jp_compose))
    list.add(Data(4,"title 4", R.drawable.bg_jp_compose))
    list.add(Data(5,"title 5", R.drawable.bg_jp_compose))
    list.add(Data(6,"title 6", R.drawable.bg_jp_compose))
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .fillMaxHeight()) {
        LazyRow(
            Modifier
                .height(100.dp)
                .fillMaxWidth()){
            items(10){
                Image(painter = painterResource(id = R.drawable.img),
                    contentDescription = null)
            }
        }

        LazyColumn{
            items(list, key = {item -> item.id}){
                Row(modifier =  Modifier.height(50.dp)) {
                    Image(painter = painterResource(id = it.img), contentDescription = null )
                    Text(text = it.title)
                }
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(100){
                Image(painter = painterResource(id = R.drawable.img),
                    contentDescription = "lazy row")
            }
        }
    }
}

@Composable
fun TestScreen(navigation: () -> Unit, string: String?){
    Jetpack_compose_demoTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "userId: $string")
            Text(text = MainActivity.SCREEN_SIGN)
            Button(onClick = navigation) {
                Text(text = "Back to screen sign")
            }

        }
    }
}
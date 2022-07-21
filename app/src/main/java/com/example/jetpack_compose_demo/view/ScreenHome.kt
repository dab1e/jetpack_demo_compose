package com.example.jetpack_compose_demo.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ScreenHome(userName: String?, viewModel: LoginViewModel) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.NestedOne,
        Screen.NestedTwo
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->  
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.
                        any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.favorite, contentDescription = null ) })
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.NestedOne.route, Modifier.padding(innerPadding)) {
            composable(Screen.NestedOne.route) { ScreenNestedOne(userName, viewModel) }
            composable(Screen.NestedTwo.route) { ScreenNestedTwo(viewModel) }
        }
    }

}
sealed class Screen(val route: String, val favorite: ImageVector) {
    object NestedOne : Screen(AppContans.SCREEN_NESTED_ONE, Icons.Filled.Favorite)
    object NestedTwo : Screen(AppContans.SCREEN_NESTED_TWO, Icons.Filled.List)
}

@Composable
fun ScreenNestedOne(userName: String?, viewModel: LoginViewModel) {
    Column(modifier = Modifier
        .background(Color.Cyan)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Hi $userName")
        Text(text = AppContans.SCREEN_NESTED_ONE)
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(viewModel.getListImage()){ url ->
                Card(modifier = Modifier
                    .background(Color.White)
                    .padding(10.dp)) {
                    Row() {
                        Image(
                            painter =  rememberAsyncImagePainter(url),
                            contentDescription = null,
                            modifier = Modifier.height(128.dp)
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(text = url)
                    }

                }

            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenNestedTwo(viewModel: LoginViewModel) {
    Column(modifier = Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = AppContans.SCREEN_NESTED_TWO)

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)){
            items(viewModel.getListImage().size, itemContent = { url->
                Card(modifier = Modifier.background(Color.White)) {
                    Image(
                        painter =  rememberAsyncImagePainter(url),
                        contentDescription = null,
                        modifier = Modifier.height(128.dp)
                    )
                }
            })
        }

       LazyVerticalGrid(cells = GridCells.Fixed(3),
           contentPadding = PaddingValues(
               start = 12.dp,
               top = 16.dp,
               end = 12.dp,
               bottom = 16.dp
           ),
           content = {
               items(viewModel.getListImage()){ url ->
                   Card(modifier = Modifier.background(Color.White)) {
                       Image(
                           painter =  rememberAsyncImagePainter(url),
                           contentDescription = null,
                           modifier = Modifier.height(128.dp)
                       )
                   }

               }
           }
       )

    }
}

class AppContans(){
    companion object{
        const val SCREEN_NESTED_ONE = "SCREEN_NESTED_ONE"
        const val SCREEN_NESTED_TWO = "SCREEN_NESTED_TWO"
    }
}
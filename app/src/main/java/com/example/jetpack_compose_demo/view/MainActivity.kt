package com.example.jetpack_compose_demo.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_HOME
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_LOGIN
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_SIGN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object{
        const val SCREEN_LOGIN = "SCREEN_LOGIN"
        const val SCREEN_HOME = "SCREEN_HOME"
        const val SCREEN_SIGN = "SCREEN_SIGN"
    }
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHostPreview()
        }
        viewModel =  HomeViewModel()

    }

    @Composable
    fun NavHostPreview() {
        val navHostController = rememberNavController()
        MyNavHost(navHostController = navHostController, startDes = SCREEN_LOGIN, viewModel, LoginViewModel())
    }

}

@Composable
fun MyNavHost(navHostController: NavHostController, startDes: String, viewModel: HomeViewModel, vmLogin: LoginViewModel){
    NavHost(navController = navHostController, startDestination = startDes){
        composable(route = SCREEN_LOGIN){
            TestLogin(navControler = navHostController, viewmodel = vmLogin)
        }
        composable(route = SCREEN_HOME){
            ScreenHome(onClickBack = {navHostController.navigate(SCREEN_LOGIN)})
        }
        composable(route = SCREEN_SIGN){
            ScreenSign(navControler = navHostController)
        }
        composable(
            "test?userId={userId}", arguments = listOf(navArgument("userId") { defaultValue = "default" })
        ) { bsEntry->
            TestScreen (navigation = {navHostController.navigate(SCREEN_LOGIN)},
                bsEntry.arguments?.getString("userId"))
        }
    }
}


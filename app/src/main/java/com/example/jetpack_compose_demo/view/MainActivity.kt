package com.example.jetpack_compose_demo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_demo.view.MainActivity.Companion.SCREEN_DEEP_LINK
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
        const val SCREEN_DEEP_LINK = "SCREEN_DEEP_LINK"
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHostPreview()
        }
        viewModel = LoginViewModel()
    }

    @Composable
    fun NavHostPreview() {
        navHostController = rememberNavController()
        MyNavHost(navHostController = navHostController, startDes = SCREEN_LOGIN, viewModel)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navHostController.handleDeepLink(intent)
    }

}

@Composable
fun MyNavHost(navHostController: NavHostController, startDes: String, vmLogin: LoginViewModel){
    val uri = "https://www.demojpc.com"
    NavHost(navController = navHostController, startDestination = startDes){
        composable(route = SCREEN_LOGIN){
            ScreenLogin(navControler = navHostController, viewmodel = vmLogin)
        }
        composable(route = "${SCREEN_HOME}?userName={userName}",
            arguments = listOf(navArgument("userName") { defaultValue = "no name" }))
        { backStackEntry->
            ScreenHome(backStackEntry.arguments?.getString("userName"), viewModel = vmLogin)
        }
        composable(route = SCREEN_SIGN) {
            ScreenSign(navControler = navHostController)
        }
    }
}




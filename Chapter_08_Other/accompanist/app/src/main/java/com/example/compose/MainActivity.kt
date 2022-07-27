package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.flowLayoutDemo.FlowLayoutDemo
import com.example.compose.insetsDemo.InsetsDemo
import com.example.compose.pagerDemo.PagerDemo
import com.example.compose.swipeRefreshDemo.SwipeRefreshDemo
import com.example.compose.systemUiController.SystemUiControllerDemo
import com.example.compose.ui.theme.AccompanistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AccompanistTheme {
                val navController = rememberNavController()
                Scaffold() {
                    NavHost(navController, startDestination = Demo.enter) {
                        composable(Demo.enter) {
                            Enter(navController)
                        }
                        composable(Demo.pagerDemo) {
                            PagerDemo()
                        }
                        composable(Demo.swipeRefreshDemo) {
                            SwipeRefreshDemo()
                        }
                        composable(Demo.systemUiControllerDemo) {
                            SystemUiControllerDemo()
                        }
                        composable(Demo.flowLayoutDemo) {
                            FlowLayoutDemo()
                        }
                        composable(Demo.insetsDemo) {
                            InsetsDemo()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Enter(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate(Demo.pagerDemo) }
        ) {
            Text("pagerDemo")
        }
        Button(
            onClick = { navController.navigate(Demo.swipeRefreshDemo) }
        ) {
            Text("Swipe Refresh Demo")
        }
        Button(
            onClick = { navController.navigate(Demo.systemUiControllerDemo) }
        ) {
            Text("System Ui Controller Demo")
        }
        Button(
            onClick = { navController.navigate(Demo.flowLayoutDemo) }
        ) {
            Text("Flow Layout Demo")
        }

        Button(
            onClick = { navController.navigate(Demo.insetsDemo) }
        ) {
            Text("insets Demo")
        }
    }
}

object Demo {
    const val enter = "enter"
    const val pagerDemo = "pagerDemo"
    const val swipeRefreshDemo = "swipeRefreshDemo"
    const val systemUiControllerDemo = "systemUiControllerDemo"
    const val flowLayoutDemo = "flowLayoutDemo"
    const val insetsDemo = "insetsDemo"
}

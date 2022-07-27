package com.example.compose.insetsDemo

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun InsetsDemo() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, useDarkIcons)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("TopAppBar")
                },
                backgroundColor = Color.Gray,
                contentPadding = WindowInsets.statusBars.asPaddingValues()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {

            }
        },
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.Black
    ) { }
}

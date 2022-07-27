package com.example.compose.systemUiController

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiControllerDemo() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    val colorPanel = listOf(
        Color.Gray,
        Color.Red,
        Color.Black,
        Color.Cyan,
        Color.Transparent,
        Color.DarkGray,
        Color.LightGray,
        Color.Yellow
    )

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, useDarkIcons)
    }
    Column(Modifier.systemBarsPadding().fillMaxSize().background(Color(0xFF0079D3))) {
        colorPanel.forEach {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(it)
                    .clickable {
                        systemUiController.setSystemBarsColor(it, useDarkIcons)
                    }
            )
        }
    }
}

package com.example.compose.pagerDemo

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(
    selectedScreen: Int,
    screens: List<Screens>,
    onClick: (targetIndex: Int) -> Unit
) {
    NavigationBar {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(screen.iconVector, contentDescription = null) },
                label = { Text(screen.label) },
                selected = selectedScreen == index,
                onClick = { onClick(index) }
            )
        }
    }
}

data class Screens(
    val label: String,
    val iconVector: ImageVector,
    val content: @Composable () -> Unit
)

package com.example.compose.pagerDemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerDemo() {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var selectedScreens by remember { mutableStateOf(0) }

    val screens = listOf(
        Screens("首页", Icons.Filled.Home) { Home() },
        Screens("我喜欢的", Icons.Filled.Favorite) { Favorite() },
        Screens("设置", Icons.Filled.Settings) { Settings() }
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedScreens,
                screens,
                onClick = {
                    selectedScreens = it
                    scope.launch { pagerState.scrollToPage(selectedScreens) }
                }
            )
        }
    ) {
        HorizontalPager(
            count = screens.size,
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            screens.forEachIndexed { index, screens ->
                when (page) {
                    index -> screens.content()
                }
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedScreens = page
        }
    }

}

@Composable
fun Home() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "1. 首页🤭",
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun Favorite() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "2. 我喜欢的❤",
            style = MaterialTheme.typography.h5
        )
    }
}
@Composable
fun Settings() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "3. 设置⚙",
            style = MaterialTheme.typography.h5
        )
    }
}

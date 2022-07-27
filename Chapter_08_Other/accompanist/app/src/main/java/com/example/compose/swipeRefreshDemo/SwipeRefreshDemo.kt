package com.example.compose.swipeRefreshDemo

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun SwipeRefreshDemo() {

    val viewModel: MyViewModel = viewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val background by animateColorAsState(
        targetValue = viewModel.background,
        animationSpec = tween(1000)
    )

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(background)
        )
    }
}

class MyViewModel: ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    private val colorPanel = listOf(
        Color.Gray,
        Color.Red,
        Color.Black,
        Color.Cyan,
        Color.DarkGray,
        Color.LightGray,
        Color.Yellow
    )

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    var background by mutableStateOf(Color.Gray)

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(1000)
            background = colorPanel.random()
            _isRefreshing.emit(false)
        }
    }
}

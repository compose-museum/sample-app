package com.example.compose.AnimateXAsStateDemo

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimateXAsStateDemo() {
    var change by remember { mutableStateOf(false) }
    var flag by remember { mutableStateOf(false) }

    val buttonSize by animateDpAsState(
        targetValue = if (change) 32.dp else 24.dp
    )
    if (buttonSize == 32.dp) {
        change = false
    }
    IconButton(
        onClick = {
            change = true
            flag = !flag
        }
    ) {
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = null,
            modifier = Modifier.size(buttonSize),
            tint = if (flag) Color.Red else Color.Gray
        )
    }
}
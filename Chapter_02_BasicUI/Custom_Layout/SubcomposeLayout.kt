package com.example.compose


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun SubcomposeRow(
    modifier: Modifier,
    text: @Composable () -> Unit,
    divider: @Composable (Int) -> Unit
){
    SubcomposeLayout(modifier = modifier) { constraints->
        var maxHeight = 0
        var placeables = subcompose("text", text).map {
            var placeable = it.measure(constraints)
            maxHeight = placeable.height.coerceAtLeast(maxHeight)
            placeable
        }
        var dividerPlaceable = subcompose("divider") {
            divider(maxHeight)
        }.map {
            it.measure(constraints.copy(minWidth = 0))
        }
        assert(dividerPlaceable.size == 1, { "There Should Be Only One LayoutNode" })
        var midPos = constraints.maxWidth / 2
        layout(constraints.maxWidth, constraints.maxHeight){
            placeables.forEach {
                it.placeRelative(0, 0)
            }
            dividerPlaceable.forEach {
                it.placeRelative(midPos, 0)
            }
        }
    }
}

@Composable
fun SubcompositionDemo() {
    Box(Modifier.fillMaxSize()) {
        SubcomposeRow(
            modifier = Modifier
                .fillMaxWidth(),
            text = {
                Text(text = "Left", Modifier.wrapContentWidth(Alignment.Start))
                Text(text = "Right", Modifier.wrapContentWidth(Alignment.End))
            }
        ) {
            var heightPx = with( LocalDensity.current) { it.toDp() }
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .width(4.dp)
                    .height(heightPx)
            )
        }
    }
}
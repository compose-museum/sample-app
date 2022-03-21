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
fun IntrinsicRow(modifier: Modifier, content: @Composable () -> Unit){
    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = object: MeasurePolicy {
            override fun MeasureScope.measure(
                measurables: List<Measurable>,
                constraints: Constraints
            ): MeasureResult {
                var devideConstraints = constraints.copy(minWidth = 0)
                var mainPlaceables = measurables.filter {
                    it.layoutId == "main"
                }.map {
                    it.measure(constraints)
                }
                var devidePlaceable = measurables.first { it.layoutId == "devider"}.measure(devideConstraints)
                var midPos = constraints.maxWidth / 2
                return layout(constraints.maxWidth, constraints.maxHeight) {
                    mainPlaceables.forEach {
                        it.placeRelative(0, 0)
                    }
                    devidePlaceable.placeRelative(midPos, 0)
                }
            }

            override fun IntrinsicMeasureScope.minIntrinsicHeight(
                measurables: List<IntrinsicMeasurable>,
                width: Int
            ): Int {
                var maxHeight = 0
                measurables.forEach {
                    maxHeight = it.minIntrinsicHeight(width).coerceAtLeast(maxHeight)
                }
                return maxHeight
            }
        }
    )
}

@Composable
fun IntrinsicDemo() {
    Box(Modifier.fillMaxSize()) {
        IntrinsicRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(text = "Left",
                Modifier
                    .wrapContentWidth(Alignment.Start)
                    .layoutId("main"))
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .layoutId("devider"))
            Text(text = "Right",
                Modifier
                    .wrapContentWidth(Alignment.End)
                    .layoutId("main"))
        }
    }
}
package com.example.compose.shimmer.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val barHeight = 10.dp
val spacerPadding = 3.dp
val roundedCornerShape = RoundedCornerShape(3.dp)
val shimmerColors = listOf(
    Color.LightGray.copy(alpha = 0.6f),
    Color.LightGray.copy(alpha = 0.2f),
    Color.LightGray.copy(alpha = 0.6f),
)

@Preview(showBackground = true)
@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerItem(brush = brush)
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItem(
    brush: Brush = Brush.linearGradient(
        listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )
    )
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(verticalArrangement = Arrangement.Center) {
                repeat(5) {
                    Spacer(modifier = Modifier.padding(spacerPadding))

                    Spacer(
                        modifier = Modifier
                            .height(barHeight)
                            .clip(roundedCornerShape)
                            .fillMaxWidth(0.7f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.padding(spacerPadding))

                }

            }

            Spacer(modifier = Modifier.width(10.dp))

            Spacer(
                modifier = Modifier
                    .size(100.dp)
                    .clip(roundedCornerShape)
                    .background(brush)
            )

        }

        repeat(3) {
            Spacer(modifier = Modifier.padding(spacerPadding))

            Spacer(
                modifier = Modifier
                    .height(barHeight)
                    .clip(roundedCornerShape)
                    .fillMaxWidth()
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(spacerPadding))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    Column(Modifier.padding(5.dp)) {
        repeat(3) {
            ShimmerItem()
        }
    }

}
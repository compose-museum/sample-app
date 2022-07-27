package com.example.compose.waveloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scale
import com.example.compose.waveloading.ui.theme.WaveLoadingDemoTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveLoadingDemo()
        }
    }
}

@Preview
@Composable
fun WaveLoadingDemo() {
    var _progress by remember { mutableStateOf(0.5f) }
    var _velocity by remember { mutableStateOf(1.0f) }
    var _amplitude by remember { mutableStateOf(0.2f) }

    val size = LocalDensity.current.run {
        200.dp.toPx().roundToInt()
    }
    val _bitmap = ImageBitmap.imageResource(id = R.drawable.logo_nba)
        .asAndroidBitmap().scale(size, size)

    WaveLoadingDemoTheme {
        Column {
            Box(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
            ) {
                WaveLoading(
                    Modifier
                        .size(200.dp)
                        .clipToBounds()
                        .align(Alignment.Center),
                    WaveConfig(_progress, _amplitude, _velocity),
                    bitmap = _bitmap
                )

            }

            LabelSlider(
                label = "Progress",
                value = _progress,
                onValueChange = { _progress = it },
                range = 0f..1f
            )

            LabelSlider(
                label = "Velocity",
                value = _velocity,
                onValueChange = { _velocity = it },
                range = 0f..1f
            )

            LabelSlider(
                label = "Amplitude",
                value = _amplitude,
                onValueChange = { _amplitude = it },
                range = 0f..1f
            )
        }


    }
}


@Composable
private fun LabelSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>
) {
    Row(Modifier.padding(start = 10.dp, end = 10.dp)) {
        Text(
            label, modifier = Modifier
                .width(100.dp)
                .align(Alignment.CenterVertically)
        )
        Slider(
            modifier = Modifier.align(Alignment.CenterVertically),
            value = value,
            onValueChange = onValueChange,
            valueRange = range
        )
    }
}

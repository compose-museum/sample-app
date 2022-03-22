package com.example.compose.waveloading

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import androidx.annotation.FloatRange
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.transform
import toGrayscale
import kotlin.math.roundToInt


private const val defaultAmlitude = 0.2f
private const val defaultVelocity = 1.0f
private const val waveDuration = 2000
private const val foreDrawAlpha = 1f
private const val scaleX = 1f
private const val scaleY = 1f

val backgroundColor = Color.LightGray
val foregroundColor = Color.Blue

private val alphaBitmap by lazy {
    Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)
}

data class WaveConfig(
    @FloatRange(from = 0.0, to = 1.0) val progress: Float,
    @FloatRange(from = 0.0, to = 1.0) val amplitude: Float,
    @FloatRange(from = 0.0, to = 1.0) val velocity: Float
)

val LocalWave = compositionLocalOf<WaveConfig> {
    error("No Local WaveConfig")
}

@Composable
fun WaveLoading(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 1.0) progress: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) amplitude: Float = defaultAmlitude,
    @FloatRange(from = 0.0, to = 1.0) velocity: Float = defaultVelocity,
    source: Bitmap,
) {

    Box(modifier) {
        CompositionLocalProvider(
            LocalWave provides WaveConfig(progress, amplitude, velocity)
        ) {
            WaveLoadingInternal(bitmap = source)
        }

    }

}

@Composable
private fun WaveLoadingInternal(bitmap: Bitmap) {

    val dp = LocalDensity.current.run {
        1.dp.toPx() //一个dp在当前设备表示的像素量（水波的绘制精度设为一个dp单位）
    }

    val transition = rememberInfiniteTransition()

    val (progress, amplitude, velocity) = LocalWave.current

    val forePaint = remember(bitmap) {
        Paint().apply {
            alpha = foreDrawAlpha
            shader = BitmapShader(
                bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
    }

    val backPaint = remember(bitmap) {
        Paint().apply {
            shader = BitmapShader(
                bitmap.toGrayscale(),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
    }

    val waves = remember {
        listOf(
            WaveAnim(waveDuration, 0f, 0f, scaleX, scaleY),
            WaveAnim((waveDuration * 0.75f).roundToInt(), 0f, 0f, scaleX, scaleY),
            WaveAnim((waveDuration * 0.5f).roundToInt(), 0f, 0f, scaleX, scaleY)
        )
    }

    val animates = waves.map { transition.animateOf(duration = it.duration) }


    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        drawIntoCanvas { canvas ->

            //draw back image
            canvas.drawRect(0f, 0f, size.width, size.height, backPaint)

            waves.forEachIndexed { index, wave ->

                canvas.withSave {

                    val maxWidth = 2 * scaleX * size.width / velocity.coerceAtLeast(0.1f)
                    val maxHeight = scaleY * size.height
                    val offsetX = maxWidth / 2 * (1 - animates[index].value) - wave.offsetX
                    val offsetY = wave.offsetY

                    canvas.translate(
                        -offsetX,
                        -offsetY
                    )

                    forePaint.shader?.transform {
                        setTranslate(offsetX, 0f)
                    }

                    canvas.drawPath(
                        wave.buildWavePath(
                            dp = dp,
                            width = maxWidth,
                            height = maxHeight,
                            amplitude = size.height * amplitude,
                            progress = progress
                        ), forePaint.apply {
                            alpha = if (index == 0) 1f else 0.5f
                        }
                    )
                }

            }
        }
    }

}

@Composable
private fun InfiniteTransition.animateOf(duration: Int) = animateFloat(
    initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
        animation = tween(duration),
        repeatMode = RepeatMode.Restart
    )
)


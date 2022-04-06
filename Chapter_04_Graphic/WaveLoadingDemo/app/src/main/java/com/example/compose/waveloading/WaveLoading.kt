package com.example.compose.waveloading

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.annotation.FloatRange
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.withSave
import androidx.core.graphics.transform
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin


private const val defaultAmlitude = 0.2f
private const val defaultVelocity = 1.0f
private const val waveDuration = 2000

data class WaveConfig(
    @FloatRange(from = 0.0, to = 1.0) val progress: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) val amplitude: Float = defaultAmlitude,
    @FloatRange(from = 0.0, to = 1.0) val velocity: Float = defaultVelocity
)


@Composable
fun WaveLoading(
    modifier: Modifier = Modifier,
    waveConfig: WaveConfig,
    bitmap: Bitmap,
) {
    val transition = rememberInfiniteTransition()
    val animates = listOf(
        1f, 0.75f, 0.5f
    ).map {
        transition.animateFloat(
            initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
                animation = tween((it * waveDuration).roundToInt()),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier.fillMaxSize()) {

        // 方式一 ：使用 DrawScope API
        drawWave(
            bitmap.asImageBitmap(),
            waveConfig,
            animates
        )

        // 方式二：使用 Canvas API
//        drawWaveWithCanvas(
//            bitmap,
//            waveConfig,
//            animates
//        )
    }

}


private fun DrawScope.drawWave(
    imageBitmap: ImageBitmap,
    waveConfig: WaveConfig,
    animates: List<State<Float>>,
) {

    drawImage(image = imageBitmap, colorFilter = run {
        val cm = ColorMatrix().apply { setToSaturation(0f) }
        ColorFilter.colorMatrix(cm)
    })

    animates.forEachIndexed { index, anim ->

        val maxWidth = 2 * size.width / waveConfig.velocity.coerceAtLeast(0.1f)
        val offsetX = maxWidth / 2 * (1 - anim.value)

        translate(-offsetX) {
            drawPath(
                path = buildWavePath(
                    width = maxWidth,
                    height = size.height,
                    amplitude = size.height * waveConfig.amplitude,
                    progress = waveConfig.progress
                ), brush = ShaderBrush(ImageShader(imageBitmap).apply {
                    transform { postTranslate(offsetX, 0f) }
                }), alpha = if (index == 0) 1f else 0.5f
            )
        }

    }

}


private fun DrawScope.drawWaveWithCanvas(
    bitmap: Bitmap,
    waveConfig: WaveConfig,
    animates: List<State<Float>>
) {

    val forePaint = Paint().apply {
        shader = BitmapShader(
            bitmap,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
    }

    val backPaint = Paint().apply {
        shader = BitmapShader(
            bitmap,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        colorFilter = run {
            val cm = ColorMatrix().apply { setToSaturation(0f) }
            ColorFilter.colorMatrix(cm)
        }
    }

    drawIntoCanvas { canvas ->

        canvas.drawRect(0f, 0f, size.width, size.height, backPaint)

        animates.forEachIndexed { index, anim ->

            canvas.withSave {

                val maxWidth = 2 * size.width / waveConfig.velocity.coerceAtLeast(0.1f)
                val offsetX = maxWidth / 2 * (1 - anim.value)

                canvas.translate(-offsetX, 0f)

                forePaint.shader?.transform {
                    setTranslate(offsetX, 0f)
                }

                canvas.drawPath(
                    buildWavePath(
                        width = maxWidth,
                        height = size.height,
                        amplitude = size.height * waveConfig.amplitude,
                        progress = waveConfig.progress
                    ), forePaint.apply {
                        alpha = if (index == 0) 1f else 0.5f
                    }
                )
            }

        }
    }

}


private fun buildWavePath(
    dp: Float = 3f,
    width: Float,
    height: Float,
    amplitude: Float,
    progress: Float
): Path {

    //调整振幅，振幅不大于剩余空间
    var adjustHeight = min(height * Math.max(0f, 1 - progress), amplitude)

    return Path().apply {
        reset()
        moveTo(0f, height)
        lineTo(0f, height * (1 - progress))
        if (progress > 0f && progress < 1f) {
            if (adjustHeight > 0) {
                var x = dp
                while (x < width) {
                    lineTo(
                        x, height * (1 - progress) - adjustHeight / 2f * sin(4.0 * Math.PI * x / width)
                            .toFloat()
                    )
                    x += dp
                }
            }
        }
        lineTo(width, height * (1 - progress))
        lineTo(width, height)
        close()
    }
}
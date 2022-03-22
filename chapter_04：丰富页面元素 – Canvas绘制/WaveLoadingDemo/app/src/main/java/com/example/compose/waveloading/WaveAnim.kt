package com.example.compose.waveloading

import androidx.compose.ui.graphics.Path
import kotlin.math.roundToInt

internal data class WaveAnim(
    val duration: Int,
    val offsetX: Float,
    val offsetY: Float,
    val scaleX: Float,
    val scaleY: Float,
) {

    private val _path = Path()

    internal fun buildWavePath(
        dp: Float,
        width: Float,
        height: Float,
        amplitude: Float,
        progress: Float
    ): Path {

        var wave = (scaleY * amplitude).roundToInt() //计算拉伸之后的波幅

        //调整振幅，振幅不大于剩余空间
        val maxWave = height * Math.max(0f, 1 - progress)
        if (wave > maxWave) {
            wave = maxWave.toInt()
        }

        _path.reset()
        _path.moveTo(0f, height)
        _path.lineTo(0f, height * (1 - progress))
        if (progress > 0f && progress < 1f) {
            if (wave > 0) {
                var x = dp
                while (x < width) {
                    _path.lineTo(
                        x,
                        height * (1 - progress) - wave / 2f * Math.sin(4.0 * Math.PI * x / width)
                            .toFloat()
                    )
                    x += dp
                }
            }
        }
        _path.lineTo(width, height * (1 - progress))
        _path.lineTo(width, height)
        _path.close()
        return _path
    }

}
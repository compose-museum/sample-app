package com.example.compose

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawBehind() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(8.dp)
            ,modifier = Modifier
                .size(100.dp)
                .drawBehind {
                    drawCircle(
                        Color(0xffe7614e),
                        18.dp.toPx() / 2,
                        center = Offset(drawContext.size.width, 0f)
                    )
                }
        ) {
            Image(painter = painterResource(id = R.drawable.diana), contentDescription = "Diana")
        }
    }
}

@Preview
@Composable
fun DrawFuwa() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var transition = rememberInfiniteTransition()
        val alpha by transition.animateFloat(initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing), repeatMode = RepeatMode.Reverse)
        )
        var context = LocalContext.current
        Box(
            modifier = Modifier
                .size(340.dp, 300.dp)
                .onGloballyPositioned {  }
                .drawWithCache {
                    val beibeiImage = ImageBitmap.imageResource(context.resources, R.drawable.beibei)
                    val jingjingImage = ImageBitmap.imageResource(context.resources, R.drawable.jingjing)
                    val huanhuanImage = ImageBitmap.imageResource(context.resources, R.drawable.huanhuan)
                    val yingyingImage = ImageBitmap.imageResource(context.resources, R.drawable.yingying)
                    val niniImage = ImageBitmap.imageResource(context.resources, R.drawable.nini)
                    onDrawBehind {
                        drawImage(
                            image = beibeiImage,
                            dstSize = IntSize(100.dp.roundToPx(), 100.dp.roundToPx()),
                            dstOffset = IntOffset.Zero,
                            alpha = alpha
                        )
                        drawImage(
                            image = jingjingImage,
                            dstSize = IntSize(100.dp.roundToPx(), 100.dp.roundToPx()),
                            dstOffset = IntOffset(120.dp.roundToPx(), 0),
                            alpha = alpha
                        )
                        drawImage(
                            image = huanhuanImage,
                            dstSize = IntSize(100.dp.roundToPx(), 100.dp.roundToPx()),
                            dstOffset = IntOffset(240.dp.roundToPx(), 0),
                            alpha = alpha
                        )
                        drawImage(
                            image = yingyingImage,
                            dstSize = IntSize(100.dp.roundToPx(), 100.dp.roundToPx()),
                            dstOffset = IntOffset(60.dp.roundToPx(), 120.dp.roundToPx()),
                            alpha = alpha
                        )
                        drawImage(
                            image = niniImage,
                            dstSize = IntSize(100.dp.roundToPx(), 100.dp.roundToPx()),
                            dstOffset = IntOffset(180.dp.roundToPx(), 120.dp.roundToPx()),
                            alpha = alpha
                        )
                    }
                }
        )
    }
}
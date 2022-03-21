package com.example.compose;

import androidx.compose.animation.core.*
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DragPlayGround() {
    var horizontalVelocity by remember {
        mutableStateOf<Float>(0f)
    }
    var verticalVelocity by remember {
        mutableStateOf<Float>(0f)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Velocity",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = String.format("Horizontal: %.2f Vertical: %.2f", horizontalVelocity, verticalVelocity),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            var offset = remember {
                Animatable(Offset.Zero, Offset.VectorConverter)
            }
            Box(
                Modifier
                    .size(350.dp)
                    .background(Color.Gray)
                    .pointerInput(Unit) {
                        offset.updateBounds(
                            lowerBound = Offset.Zero,
                            upperBound = Offset(320.dp.toPx(), 320.dp.toPx())
                        )
                        coroutineScope {
                            forEachGesture {
                                val down =  awaitPointerEventScope { awaitFirstDown() }
                                offset.stop()
                                awaitPointerEventScope {
                                    var validDrag: PointerInputChange?
                                    do {
                                        validDrag = awaitTouchSlopOrCancellation(down.id) { change, _ ->
                                            change.consumePositionChange()
                                        }
                                    } while (validDrag != null && !validDrag.positionChangeConsumed())
                                    if (validDrag != null) {
                                        val velocityTracker = VelocityTracker()
                                        var dragAnimJob: Job? = null
                                        drag(validDrag.id) {
                                            dragAnimJob = launch {
                                                offset.snapTo(
                                                    offset.value + it.positionChange()
                                                )
                                                velocityTracker.addPosition(
                                                    it.uptimeMillis,
                                                    it.position
                                                )
                                                horizontalVelocity = velocityTracker.calculateVelocity().x
                                                verticalVelocity = velocityTracker.calculateVelocity().y
                                            }
                                        }
                                        horizontalVelocity = velocityTracker.calculateVelocity().x
                                        verticalVelocity = velocityTracker.calculateVelocity().y
                                        val decay = splineBasedDecay<Offset>(this)
                                        var targetOffset = decay.calculateTargetValue(Offset.VectorConverter, offset.value, Offset(horizontalVelocity, verticalVelocity)).run {
                                            Offset(x.coerceIn(0f, 320.dp.toPx()), y.coerceIn(0f, 320.dp.toPx()))
                                        }
                                        dragAnimJob?.cancel()
                                        launch {
                                            offset.animateTo(targetOffset, tween(2000, easing = LinearOutSlowInEasing))
                                        }
                                    }
                                }
                            }
                        }
                    }
            ) {
                Box(modifier = Modifier
                    .offset {
                        IntOffset(
                            x = offset.value.x.roundToInt(),
                            y = offset.value.y.roundToInt()
                        )
                    }
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
                )
            }
        }
    }
}

@Composable
fun DragToDismiss() {
    var offset = remember {
        Animatable(Offset.Zero, Offset.VectorConverter)
    }

    Box(modifier = Modifier
        .size(30.dp)
        .clip(CircleShape)
        .background(Color.Green)
        .offset { IntOffset(x = offset.value.x.roundToInt(), y = offset.value.y.roundToInt()) }
        .pointerInput(Unit) {
            val decay = splineBasedDecay<Offset>(this)
            coroutineScope {
                forEachGesture {
                    val pointerId = awaitPointerEventScope {
                        awaitFirstDown().id
                    }
                    val velocityTracker = VelocityTracker()
                    awaitPointerEventScope {
                        drag(pointerId) {
                            launch {
                                offset.snapTo(it.position)
                            }
                            velocityTracker.addPosition(
                                it.uptimeMillis,
                                it.position
                            )
                            velocityTracker.calculateVelocity()
                        }
                    }
                    val velocity = velocityTracker.calculateVelocity().x
                }
            }
        }
    )
}
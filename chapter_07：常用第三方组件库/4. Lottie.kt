package com.example.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import kotlin.math.max


@Preview
@Composable
fun LottieDemo() {
    var isPlaying by remember {
        mutableStateOf(true)
    }
    var speed by remember {
        mutableStateOf(1f)
    }

    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie),
    )

    val lottieAnimationState by animateLottieCompositionAsState (
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Lottie Animation In Jetpack Compose",
                fontSize = 30.sp
            )
            LottieAnimation(
                lottieComposition,
                lottieAnimationState,
                modifier = Modifier.size(300.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = {
                            speed = max(speed - 0.25f, 0f)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF0F9D58)
                        )
                    ) {
                        Text(
                            text = "-",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,

                            )
                    }

                    Text(
                        text = "Speed ( $speed ) ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp, modifier = Modifier.padding(horizontal = 10.dp)

                    )
                    Button(
                        onClick = {
                            speed += 0.25f
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF0F9D58)
                        )
                    ) {
                        Text(
                            text = "+",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                Button(
                    onClick = {
                        isPlaying = !isPlaying
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF0F9D58)
                    )
                ) {
                    Text(
                        text = if (isPlaying) "Pause" else "Play",
                        color = Color.White
                    )
                }
            }
        }
    }
}
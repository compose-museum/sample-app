package com.example.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.*
import coil.request.ImageRequest


@Composable
fun AsyncImageDemo() {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://pic-go-bed.oss-cn-beijing.aliyuncs.com/img/20220316151929.png")
            .crossfade(true)
            .build(),
        contentDescription = "compose",
        placeholder = painterResource(id = R.drawable.place_holder),
        error = painterResource(id = R.drawable.error),
        onSuccess = {
            Log.d(TAG, "success")
        }
    )
}

@Composable
fun SubcomposeAsyncImageDemo() {
    SubcomposeAsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data("https://pic-go-bed.oss-cn-beijing.aliyuncs.com/img/20220316151929.png")
            .build(),
        contentDescription = "compose_museum"
    ) {
        if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun AsyncImagePainterDemo() {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://pic-go-bed.oss-cn-beijing.aliyuncs.com/img/20220316151929.png")
            .build()
    )

    if (painter.state is AsyncImagePainter.State.Success) {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}
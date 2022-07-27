package com.example.compose.flowLayoutDemo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FlowLayoutDemo() {
    Surface(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        FlowRow(
            modifier = Modifier.padding(8.dp),
            crossAxisSpacing = 12.dp,
            mainAxisSpacing = 10.dp
        ) {
            Tag(
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.wechat), null, tint = Color.White)
                },
                text = "WeChat",
                elevation = 6.dp,
                textStyle = TextStyle(Color.White),
                backgroundColor = Color(0xFF07C160)
            ) { }
            Tag(
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.twitter), null, tint = Color.White)
                },
                text = "Twitter",
                elevation = 6.dp,
                textStyle = TextStyle(Color.White),
                backgroundColor = Color(0xFF1DA1F2)
            ) { }
            Tag(
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.github), null, tint = Color.White)
                },
                text = "Github",
                elevation = 6.dp,
                textStyle = TextStyle(Color.White),
                backgroundColor = Color(0xFF181717)
            ) { }
            Tag(
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.microsoftedge), null, tint = Color(0xFF0078D7))
                },
                text = "Edge",
                elevation = 6.dp
            ) { }
            Tag(
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.microsoft), null, tint = Color(0xFF5E5E5E))
                },
                text = "Microsoft",
                elevation = 6.dp
            ) { }
        }
    }
}

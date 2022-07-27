package com.example.compose.flowLayoutDemo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    elevation: Dp = 0.dp,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    text: String,
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    backgroundColor: Color = Color(0xFFE8E8E8),
    border: BorderStroke? = null,
    onClick:() -> Unit
){

    Surface(
        shape = shape,
        color = backgroundColor,
        modifier = modifier,
        elevation = elevation,
        border = border
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    onClick = onClick
                )
                .padding(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when{
                leadingIcon != null -> {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        content = leadingIcon
                    )
                    Spacer(Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = text,
                        style = textStyle,
                    )
                }
                trailingIcon != null -> {
                    Text(
                        text = text,
                        style = textStyle,
                    )
                    Spacer(Modifier.padding(horizontal = 4.dp))
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        content = trailingIcon
                    )
                }
                else -> {
                    Text(
                        text = text,
                        style = textStyle,
                    )
                }
            }
        }
    }
}

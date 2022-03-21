package com.example.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension



@Preview
@Composable
fun QuotesDemo() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        val (quotesFirstLineRef, quotesSecondLineRef, quotesThirdLineRef, quotesForthLineRef) = remember { createRefs() }
        createVerticalChain(quotesFirstLineRef, quotesSecondLineRef, quotesThirdLineRef, quotesForthLineRef, chainStyle = ChainStyle.Spread)
        Text(
            text = "寄蜉蝣于天地，",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(quotesFirstLineRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = "渺沧海之一粟。",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(quotesSecondLineRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = "哀吾生之须臾，",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(quotesThirdLineRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = "羡长江之无穷。",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(quotesForthLineRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun UserPortraitDemo() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        var (userPortraitBackgroundRef, userPortraitImgRef, welcomeRef, quotesRef) = remember { createRefs() }
        var guideLine = createGuidelineFromTop(0.2f)
        Box(modifier = Modifier
            .constrainAs(userPortraitBackgroundRef) {
                top.linkTo(parent.top)
                bottom.linkTo(guideLine)
                height = Dimension.fillToConstraints
                width = Dimension.matchParent
            }
            .background(Color(0xFF1E9FFF))
        )
        Image(painter = painterResource(id = R.drawable.boy_portrait),
            contentDescription = "portrait",
            modifier = Modifier
                .constrainAs(userPortraitImgRef) {
                    top.linkTo(guideLine)
                    bottom.linkTo(guideLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(100.dp)
                .clip(CircleShape)
                .border(width = 2.dp, color = Color(0xFF5FB878), shape = CircleShape))
        Text(
            text = "Compose 技术爱好者",
            color = Color.White,
            fontSize = 26.sp,
            modifier = Modifier.constrainAs(welcomeRef) {
                top.linkTo(userPortraitImgRef.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun InputFieldLayoutDemo() {
    ConstraintLayout(
        modifier = Modifier
            .width(400.dp)
            .padding(10.dp)
    ) {
        val (usernameTextRef, passwordTextRef, usernameInputRef, passWordInputRef, dividerRef) = remember { createRefs() }
        var barrier = createEndBarrier(usernameTextRef, passwordTextRef)
        Text(
            text = "用户名",
            fontSize = 14.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .constrainAs(usernameTextRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .constrainAs(dividerRef) {
                    top.linkTo(usernameTextRef.bottom)
                    bottom.linkTo(passwordTextRef.top)
                })
        Text(
            text = "密码",
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(passwordTextRef) {
                    top.linkTo(usernameTextRef.bottom, 19.dp)
                    start.linkTo(parent.start)
                }
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(usernameInputRef) {
                start.linkTo(barrier, 10.dp)
                top.linkTo(usernameTextRef.top)
                bottom.linkTo(usernameTextRef.bottom)
                height = Dimension.fillToConstraints
            }
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(passWordInputRef) {
                start.linkTo(barrier, 10.dp)
                top.linkTo(passwordTextRef.top)
                bottom.linkTo(passwordTextRef.bottom)
                height = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
fun ConstraintLayoutDemo() {
    ConstraintLayout(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .padding(10.dp)
    ) {
        val (portraitImageRef, usernameTextRef, desTextRef) = remember { createRefs() }
        Image(
            painter = painterResource(id = R.drawable.user_portrait),
            contentDescription = "portrait",
            modifier = Modifier.constrainAs(portraitImageRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "Compose 技术爱好者",
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .constrainAs(usernameTextRef) {
                    top.linkTo(portraitImageRef.top)
                    start.linkTo(portraitImageRef.end, 10.dp)
                    width = Dimension.preferredWrapContent

                }
        )
        Text(
            text = "我的个人描述...",
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .constrainAs(desTextRef) {
                    top.linkTo(usernameTextRef.bottom, 5.dp)
                    start.linkTo(portraitImageRef.end, 10.dp)
                }
        )
    }
}
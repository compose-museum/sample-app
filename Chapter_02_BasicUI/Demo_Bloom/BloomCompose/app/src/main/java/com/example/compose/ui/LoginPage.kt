/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.*


@Preview(showBackground = true)
@Composable
fun TopText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var keywordPre = "By Clicking below you agree to our".split(" ")
        var keywordPost = "and consent".split(" ")
        for (word in keywordPre) {
            Text(
                text = word,
                style = body2,
                color = gray,
            )
        }
        Text(
            text = "Terms of Use",
            style = body2,
            color = gray,
            textDecoration = TextDecoration.Underline
        )
        for (word in keywordPost) {
            Text(
                text = word,
                style = body2,
                color = gray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = " to Our ",
            style = body2,
            color = gray
        )
        Text(text = "Privacy Policy.",
            style = body2,
            color = gray,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HintWithUnderline() {
    Column(
        modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp)
    ){
        TopText()
        BottomText()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginInputBox() {
    Column {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(small),
            placeholder = {
                Text(
                    text = "Email address",
                    style = body1,
                    color = gray
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(small),
            placeholder = {
                Text(
                    text = "Password(8+ Characters)",
                    style = body1,
                    color = gray
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginTitle() {
    Text(
        text = "Log in with email",
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(top = 184.dp, bottom = 16.dp),
        style = h1,
        color = gray,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun LoginButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(medium),
        colors = ButtonDefaults.buttonColors(backgroundColor = pink900)
    ) {
        Text(
            text = "Log in",
            style = button,
            color = white
        )
    }
}

@Composable
fun LoginPage() {
    Column(
        Modifier
            .fillMaxSize()
            .background(white)
            .padding(horizontal = 16.dp)
    ) {
        LoginTitle()
        LoginInputBox()
        HintWithUnderline()
        LoginButton()
    }
}

@Composable
@Preview
fun LoginPageLightPreview() {
    BloomTheme() {
        LoginPage()
    }
}

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
package com.example.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.R

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val nunitoSansFamily = FontFamily(
    Font(R.font.nunitosans_light, FontWeight.Light),
    Font(R.font.nunitosans_semibold, FontWeight.SemiBold),
    Font(R.font.nunitosans_bold, FontWeight.Bold)
)

val h1 = TextStyle(
    fontSize = 18.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Bold
)
val h2 = TextStyle(
    fontSize = 14.sp,
    letterSpacing = 0.15.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Bold
)
val subtitle1 = TextStyle(
    fontSize = 16.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light
)
val body1 = TextStyle(
    fontSize = 14.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light
)
val body2 = TextStyle(
    fontSize = 12.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light
)
val button = TextStyle(
    fontSize = 14.sp,
    letterSpacing = 1.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.SemiBold,
    color = white
)
val caption = TextStyle(
    fontSize = 12.sp,
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.SemiBold
)

val bloomTypoGraphy = Typography(
    h1 = TextStyle(
        fontSize = 18.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.Bold
    ),
    h2 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.15.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.Bold
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.Light
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.Light
    ),
    body2 = TextStyle(
        fontSize = 12.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.Light
    ),
    button = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 1.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.SemiBold,
        color = white
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontFamily = nunitoSansFamily,
        fontWeight = FontWeight.SemiBold
    )
)

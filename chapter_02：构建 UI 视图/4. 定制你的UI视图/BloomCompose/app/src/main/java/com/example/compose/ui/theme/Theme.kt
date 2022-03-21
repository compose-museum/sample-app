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

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.compose.R

private val BloomLightColorPaltte = lightColors(
    primary = pink100,
    secondary = pink900,
    background = white,
    surface = white850,
    onPrimary = gray,
    onSecondary = white,
    onBackground = gray,
    onSurface = gray,
)

private val BloomDarkColorPaltte = darkColors(
    primary = green900,
    secondary = green300,
    background = gray,
    surface = white150,
    onPrimary = white,
    onSecondary = gray,
    onBackground = white,
    onSurface = white850
)

open class WelcomeAssets private constructor(
    var background: Int,
    var illos: Int,
    var logo: Int
) {
    object LightWelcomeAssets : WelcomeAssets(
        background = R.drawable.ic_light_welcome_bg,
        illos = R.drawable.ic_light_welcome_illos,
        logo = R.drawable.ic_light_logo
    )

    object DarkWelcomeAssets : WelcomeAssets(
        background = R.drawable.ic_dark_welcome_bg,
        illos = R.drawable.ic_dark_welcome_illos,
        logo = R.drawable.ic_dark_logo
    )
}

internal var LocalWelcomeAssets = staticCompositionLocalOf { WelcomeAssets.LightWelcomeAssets as WelcomeAssets }

val MaterialTheme.welcomeAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalWelcomeAssets.current

enum class BloomTheme {
    LIGHT, DARK
}

@Composable
fun BloomTheme(theme: BloomTheme = BloomTheme.LIGHT, content: @Composable() () -> Unit) {
    CompositionLocalProvider(
        LocalWelcomeAssets provides if (theme == BloomTheme.DARK) WelcomeAssets.DarkWelcomeAssets else WelcomeAssets.LightWelcomeAssets,
    ) {
        MaterialTheme(
            colors = if (theme == BloomTheme.DARK) BloomDarkColorPaltte else BloomLightColorPaltte,
            typography = bloomTypoGraphy,
            shapes = shapes,
            content = content
        )
    }
}

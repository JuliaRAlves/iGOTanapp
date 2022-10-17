package com.junyidark.igotanapp.presentation.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val IronHandPalette = lightColors(
    background = CrackedPepper,
    primary = YellowCorn,
    secondary = DeepAubergine,
    onSecondary = Sterling,
    onPrimary = RegularBlack
)

private val IntoTheSunPalette = lightColors(
    background = LaFondaSpice,
    primary = Sunglow,
    secondary = RedOchre,
    onSecondary = GreenHighlands,
    onPrimary = RegularBlack
)

private val DeepRiversPalette = lightColors(
    background = RoycroftPewter,
    primary = RoycroftMistGray,
    secondary = Bosporus,
    onSecondary = TimidBlue,
    onPrimary = RegularBlack
)

@Composable
fun IGOTanappTheme(
    intoTheSunEnabled: Boolean = false,
    deepRiversEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = when {
        intoTheSunEnabled -> IntoTheSunPalette
        deepRiversEnabled -> DeepRiversPalette
        else -> IronHandPalette
    }

//    val view = LocalView.current
//    val window = (view.context as Activity).window
//    window.statusBarColor = colors.secondary.toArgb()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
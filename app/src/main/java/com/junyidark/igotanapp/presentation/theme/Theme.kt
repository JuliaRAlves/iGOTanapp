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
    onPrimary = RegularBlack,
    error = LightCyan,
    onError = SteelBlue
)

private val IntoTheSunPalette = lightColors(
    background = LaFondaSpice,
    primary = Sunglow,
    secondary = RedOchre,
    onSecondary = GreenHighlands,
    onPrimary = RegularBlack,
    error = LightCyan,
    onError = SteelBlue
)

private val DeepRiversPalette = lightColors(
    background = RoycroftPewter,
    primary = RoycroftMistGray,
    secondary = Bosporus,
    onSecondary = TimidBlue,
    onPrimary = RegularBlack,
    error = LightCyan,
    onError = SteelBlue
)

@Composable
fun IGOTanappTheme(
    theme: Theme,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        Theme.INTO_THE_SUN -> IntoTheSunPalette
        Theme.DEEP_RIVERS -> DeepRiversPalette
        Theme.IRON_HAND -> IronHandPalette
    }

    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = colors.secondary.toArgb()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

enum class Theme {
    INTO_THE_SUN,
    DEEP_RIVERS,
    IRON_HAND
}

const val THEME_PREFERENCE = "THEME_PREFERENCE"
package com.junyidark.igotanapp.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

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
    theme: Theme = Theme.IRON_HAND,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        Theme.INTO_THE_SUN -> IntoTheSunPalette
        Theme.DEEP_RIVERS -> DeepRiversPalette
        Theme.IRON_HAND -> IronHandPalette
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

enum class Theme {
    INTO_THE_SUN,
    DEEP_RIVERS,
    IRON_HAND
}
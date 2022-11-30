package com.junyidark.igotanapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.junyidark.igotanapp.R

val TrajanPro = FontFamily(
    Font(R.font.trajan_pro)
)

val Typography = Typography(
    overline = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h2 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    h3 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
)
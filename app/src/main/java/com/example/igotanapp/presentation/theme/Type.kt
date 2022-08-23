package com.example.igotanapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.igotanapp.R

val TrajanPro = FontFamily(
    Font(R.font.trajan_pro)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = TrajanPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
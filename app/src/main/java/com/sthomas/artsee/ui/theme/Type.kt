package com.sthomas.artsee.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 1.25.sp
    ),
)
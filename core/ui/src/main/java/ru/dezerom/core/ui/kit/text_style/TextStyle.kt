package ru.dezerom.core.ui.kit.text_style

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.dezerom.core.tools.consts.Colors

val TS = Typography(
    headlineMedium = TextStyle(
        color = Colors.white,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = TextStyle(
        color = Colors.white,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        color = Colors.white,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = TextStyle(
        color = Colors.white,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        color = Colors.white,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        color = Colors.white,
        fontSize = 10.sp,
        fontWeight = FontWeight.Light
    )
)
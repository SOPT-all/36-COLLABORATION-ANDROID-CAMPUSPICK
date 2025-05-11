package org.sopt.collaboration.campuspick.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF1ACDFF)
val Yellow = Color(0xFFFFCC1C)
val SKyBlue = Color(0xFFF1FAFC)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val BlackTransparent = Color(0x99000000)

val Gray1 = Color(0xFF5C5C5C)
val Gray2 = Color(0xFFA6A6A6)
val Gray3 = Color(0xFFD6D6D6)
val Gray4 = Color(0xFFF2F2F2)
val Gray5 = Color(0xFFF7F7F7)

@Immutable
data class CampuspickColors(
    val Blue: Color,
    val Yellow: Color,
    val SkyBlue: Color,

    val White: Color,
    val Black: Color,
    val BlackTransparent: Color,

    val Gray1: Color,
    val Gray2: Color,
    val Gray3: Color,
    val Gray4: Color,
    val Gray5: Color
)

val defaultCampuspickColors = CampuspickColors(
    Blue = Blue,
    Yellow = Yellow,
    SkyBlue = SKyBlue,

    White = White,
    Black = Black,
    BlackTransparent = BlackTransparent,

    Gray1 = Gray1,
    Gray2 = Gray2,
    Gray3 = Gray3,
    Gray4 = Gray4,
    Gray5 = Gray5
)

val LocalCampuspickColorsProvider = staticCompositionLocalOf { defaultCampuspickColors }
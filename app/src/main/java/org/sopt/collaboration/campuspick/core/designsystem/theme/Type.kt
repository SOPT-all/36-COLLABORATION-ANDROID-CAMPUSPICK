package org.sopt.collaboration.campuspick.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class CampuspickTypography(
    val heading0: TextStyle,
    val heading1: TextStyle,
    val heading2: TextStyle,
    val heading3: TextStyle,
    val heading4: TextStyle,

    val body0: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,

    val caption1: TextStyle,
    val caption2: TextStyle,
    val caption3: TextStyle,
    val caption4: TextStyle
)

private fun CampuspickTextSytle(
    fontFamily: FontFamily = FontFamily.Default,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    lineHeight: TextUnit = 1.em,
    letterSpacing: TextUnit = (-0.002).em
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

val defaultCampuspickTypography = CampuspickTypography(
    heading0 = CampuspickTextSytle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    heading1 = CampuspickTextSytle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    heading2 = CampuspickTextSytle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    heading3 = CampuspickTextSytle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    heading4 = CampuspickTextSytle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    body0 = CampuspickTextSytle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = CampuspickTextSytle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    body2 = CampuspickTextSytle(
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    body3 = CampuspickTextSytle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    ),
    caption1 = CampuspickTextSytle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    caption2 = CampuspickTextSytle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    caption3 = CampuspickTextSytle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    caption4 = CampuspickTextSytle(
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    )
)

val LocalCampuspickTypographyProvider = staticCompositionLocalOf { defaultCampuspickTypography }
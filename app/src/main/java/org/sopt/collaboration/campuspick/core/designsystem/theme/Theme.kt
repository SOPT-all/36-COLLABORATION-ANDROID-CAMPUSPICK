package org.sopt.collaboration.campuspick.core.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object CampuspickTheme {
    val colors: CampuspickColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCampuspickColorsProvider.current

    val typography: CampuspickTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalCampuspickTypographyProvider.current
}

@Composable
fun ProvideCampuspickColorsAndTypography(
    colors: CampuspickColors,
    typography: CampuspickTypography,
    content: @Composable () -> Unit
){
    CompositionLocalProvider(
        LocalCampuspickColorsProvider provides colors,
        LocalCampuspickTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun CampuspickTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    ProvideCampuspickColorsAndTypography(
        colors = defaultCampuspickColors,
        typography = defaultCampuspickTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode){
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this,view).isAppearanceLightStatusBars = false
                }
            }
        }
    }

    MaterialTheme(
        content = content
    )
}
package com.healthtracking.app.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.healthtracking.app.R

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = ErrorRed,
    errorContainer = ErrorRedDark,
    onError = Color.White,
    surfaceVariant = DisabledGrayDark,
    surfaceDim = DimTertiaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,
    background = LightGray,
    surface = LightGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = TertiaryGray,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = ErrorRed,
    errorContainer = ErrorRedLight,
    onError = Color.White,
    surfaceVariant = DisabledGrayLight,
    surfaceDim = DimTertiaryLight
)

val primaryFont = FontFamily(
    Font(R.font.primary_font, FontWeight.Normal)
)

val CustomCutCornerShape = CutCornerShape(bottomStart = 8.dp, topEnd = 8.dp)

@Composable
fun HealthTrackingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
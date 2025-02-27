package com.example.weather.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primaryContainer = MidnightTeal,
    onPrimaryContainer = Cream,
    background = GunmetalGray,
    onBackground = Cream,
    secondaryContainer = BlueSky,
    onSecondaryContainer = GunmetalGray
)

private val LightColorScheme = lightColorScheme(
    primaryContainer = MidnightTeal,
    onPrimaryContainer = Cream,
    background = GunmetalGray,
    onBackground = Cream,
    secondaryContainer = BlueSky,
    onSecondaryContainer = GunmetalGray,
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

package com.example.weather.core.presentation

import androidx.activity.SystemBarStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

@Composable
fun StatusBar(
    lightTheme: Boolean,
    content: (SystemBarStyle) -> Unit,
) {
    val barColor = Color.White.toArgb()

    LaunchedEffect(lightTheme) {
        if (lightTheme) {
            content(
                SystemBarStyle.light(
                    barColor, barColor,
                ),
            )
        } else {
            content(
                SystemBarStyle.dark(
                    barColor,
                )
            )
        }
    }
}

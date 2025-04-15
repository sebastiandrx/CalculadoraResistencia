package com.example.codigocolores.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF8AB4F8),
    secondary = Color(0xFF80CBC4),
    background = Color(0xFF000000),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFFFFC107),
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black
)

@Composable
fun CalcularResistenciaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

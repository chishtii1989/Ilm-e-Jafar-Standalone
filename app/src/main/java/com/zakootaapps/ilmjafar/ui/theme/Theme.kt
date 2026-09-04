package com.zakootaapps.ilmjafar.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =  darkColorScheme(
    primary = ZakootaGreen,
    onPrimary = Color.White,
    primaryContainer = ZakootaGreenDark,
    onPrimaryContainer = Color(0xFFC8F6E0),
    secondary = ZakootaSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF146B4A),
    onSecondaryContainer = Color(0xFFD6F0E3),
    tertiary = ZakootaGreenDark,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF0B5D3B),
    onTertiaryContainer = Color(0xFFC8F6E0),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = ZakootaBackgroundDark,
    onBackground = Color.White,
    surface = ZakootaSurfaceDark,
    onSurface = Color.White,
    surfaceVariant = ZakootaSurfaceVariantDark,
    onSurfaceVariant = Color.White.copy(alpha = 0.8f),
    outline = Color(0xFF8B938E)
)

private val LightColorScheme =  lightColorScheme(
    primary = ZakootaGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8F6E0),
    onPrimaryContainer = Color(0xFF0B5D3B),
    secondary = ZakootaSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD6F0E3),
    onSecondaryContainer = Color(0xFF0B5D3B),
    tertiary = ZakootaGreenDark,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFC8F6E0),
    onTertiaryContainer = Color(0xFF0B5D3B),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color.White,
    onErrorContainer = Color(0xFF410002),
    background = ZakootaBackgroundLight,
    onBackground = ZakootaTextDark,
    surface = ZakootaSurfaceLight,
    onSurface = ZakootaTextDark,
    surfaceVariant = ZakootaSurfaceVariantLight,
    onSurfaceVariant = ZakootaTextMuted,
    outline = Color(0xFF707973)
)

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, shapes = AppShapes) {
      content()
  }
}

package com.example.goalfeed.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary       = White,
    onPrimary     = Black,
    secondary     = CardTextBlue,
    onSecondary   = White,
    background    = DarkBackground,
    onBackground  = OnDark,
    surface       = DarkSurface,
    onSurface     = OnDark,
    error         = RedLive,
    onError       = White
)

private val LightColorScheme = lightColorScheme(
    primary       = Black,
    onPrimary     = White,
    secondary     = CardTextBlue,
    onSecondary   = White,
    background    = LightBackground,
    onBackground  = Black,
    surface       = LightCardBackground,
    onSurface     = Black,
    error         = RedLive,
    onError       = White
)

@Composable
fun GoalFeedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography  = Typography,
        content     = content
    )
}

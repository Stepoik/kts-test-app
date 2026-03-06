package ru.stepan.reddit.uikit

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val RedditOrange = Color(0xFFFF4500)   // основной бренд
val RedditOrangeLight = Color(0xFFFF6A33)
val RedditOrangeDark = Color(0xFFD73700)

val RedditBlue = Color(0xFF0079D3)
val RedditBlueLight = Color(0xFF4DA3FF)
val RedditBlueDark = Color(0xFF005999)

val RedditWhite = Color(0xFFFFFFFF)
val RedditBlack = Color(0xFF030303)
val RedditGray100 = Color(0xFFF6F7F8)
val RedditGray200 = Color(0xFFEDEFF1)
val RedditGray300 = Color(0xFFD7DADC)
val RedditGray600 = Color(0xFF818384)
val RedditGray800 = Color(0xFF1A1A1B)
val RedditGray900 = Color(0xFF121212)

val Yellow = Color(0xFFF3AB34)

val RedditLightColorScheme = lightColorScheme(
    primary = RedditOrange,
    onPrimary = Color.White,
    primaryContainer = RedditOrangeLight,
    onPrimaryContainer = Color.Black,

    secondary = RedditBlue,
    onSecondary = Color.White,
    secondaryContainer = RedditBlueLight,
    onSecondaryContainer = Color.Black,

    tertiary = Yellow,

    background = RedditWhite,
    onBackground = RedditBlack,

    surface = RedditGray100,
    onSurface = RedditBlack,

    surfaceVariant = RedditGray200,
    onSurfaceVariant = Color(0xFF3C3C3C),

    outline = RedditGray300,

    error = Color(0xFFB00020),
    onError = Color.White
)

val RedditDarkColorScheme = darkColorScheme(
    primary = RedditOrange,
    onPrimary = Color.Black,
    primaryContainer = RedditOrangeDark,
    onPrimaryContainer = Color.White,

    secondary = RedditBlue,
    onSecondary = Color.Black,
    secondaryContainer = RedditBlueDark,
    onSecondaryContainer = Color.White,

    tertiary = Yellow,

    background = RedditGray900,
    onBackground = Color.White,

    surface = RedditGray800,
    onSurface = Color.White,

    surfaceVariant = Color(0xFF2A2A2B),
    onSurfaceVariant = RedditGray600,

    outline = Color(0xFF3A3A3C),

    error = Color(0xFFCF6679),
    onError = Color.Black
)
package ru.stepan.reddit.uikit

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val StepikGreen = Color(0xFF5BCF5B)       // основной бренд
val StepikGreenLight = Color(0xFF7DDB7D)
val StepikGreenDark = Color(0xFF3EA63E)

val StepikBlue = Color(0xFF2979FF)
val StepikBlueLight = Color(0xFF5C9DFF)
val StepikBlueDark = Color(0xFF1C54B2)

val StepikWhite = Color(0xFFFFFFFF)
val StepikBlack = Color(0xFF121212)

val StepikGray100 = Color(0xFFF5F5F5)
val StepikGray200 = Color(0xFFEAEAEA)
val StepikGray300 = Color(0xFFD6D6D6)
val StepikGray600 = Color(0xFF7A7A7A)
val StepikGray800 = Color(0xFF2A2A2A)
val StepikGray900 = Color(0xFF1B1B1B)

val StepikYellow = Color(0xFFFFC107)

val StepikSurfaceContainerLowestLight = Color(0xFFFFFFFF)
val StepikSurfaceContainerLowLight = Color(0xFFF7F7F7)
val StepikSurfaceContainerLight = Color(0xFFF1F1F1)
val StepikSurfaceContainerHighLight = Color(0xFFEAEAEA)
val StepikSurfaceContainerHighestLight = Color(0xFFE3E3E3)

val StepikSurfaceContainerLowestDark = Color(0xFF1B1B1B)
val StepikSurfaceContainerLowDark = Color(0xFF232323)
val StepikSurfaceContainerDark = Color(0xFF2A2A2A)
val StepikSurfaceContainerHighDark = Color(0xFF323232)
val StepikSurfaceContainerHighestDark = Color(0xFF3A3A3A)

val StepikLightColorScheme = lightColorScheme(
    primary = StepikGreen,
    onPrimary = Color.White,
    primaryContainer = StepikGreenLight,
    onPrimaryContainer = Color.Black,

    secondary = StepikBlue,
    onSecondary = Color.White,
    secondaryContainer = StepikBlueLight,
    onSecondaryContainer = Color.Black,

    tertiary = StepikYellow,

    background = StepikWhite,
    onBackground = StepikBlack,

    surface = StepikGray100,
    onSurface = StepikBlack,

    surfaceVariant = StepikGray200,
    onSurfaceVariant = Color(0xFF3C3C3C),

    surfaceContainerLowest = StepikSurfaceContainerLowestLight,
    surfaceContainerLow = StepikSurfaceContainerLowLight,
    surfaceContainer = StepikSurfaceContainerLight,
    surfaceContainerHigh = StepikSurfaceContainerHighLight,
    surfaceContainerHighest = StepikSurfaceContainerHighestLight,

    outline = StepikGray300,

    error = Color(0xFFB00020),
    onError = Color.White
)

val StepikDarkColorScheme = darkColorScheme(
    primary = StepikGreen,
    onPrimary = Color.Black,
    primaryContainer = StepikGreenDark,
    onPrimaryContainer = Color.White,

    secondary = StepikBlue,
    onSecondary = Color.Black,
    secondaryContainer = StepikBlueDark,
    onSecondaryContainer = Color.White,

    tertiary = StepikYellow,

    background = StepikGray900,
    onBackground = Color.White,

    surface = StepikGray800,
    onSurface = Color.White,

    surfaceVariant = Color(0xFF333333),
    onSurfaceVariant = StepikGray600,

    surfaceContainerLowest = StepikSurfaceContainerLowestDark,
    surfaceContainerLow = StepikSurfaceContainerLowDark,
    surfaceContainer = StepikSurfaceContainerDark,
    surfaceContainerHigh = StepikSurfaceContainerHighDark,
    surfaceContainerHighest = StepikSurfaceContainerHighestDark,

    outline = Color(0xFF444444),

    error = Color(0xFFCF6679),
    onError = Color.Black
)
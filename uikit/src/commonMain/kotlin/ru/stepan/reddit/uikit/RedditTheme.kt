package ru.stepan.reddit.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RedditTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(colorScheme = provideColors(isDarkTheme), typography = RedditTypography) {
        CompositionLocalProvider(LocalDimens provides MobileDimens, content = content)
    }
}

@Composable
fun provideColors(isDarkTheme: Boolean): ColorScheme {
    return if (isDarkTheme) {
        StepikDarkColorScheme
    } else {
        StepikLightColorScheme
    }
}

val RedditTypography = Typography(
    // Большие заголовки (редко используются)
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),

    displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),

    displaySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),

    // Заголовки экранов
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),

    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    // Заголовок поста
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Основной текст поста / комментария
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // Подписи, никнеймы, счетчики
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
)

data class Dimens(
    val paddings: Paddings,
    val sizes: Sizes
) {
    data class Paddings(
        val xs: Dp,
        val sm: Dp,
        val md: Dp,
        val lg: Dp,
        val xl: Dp,
        val xxl: Dp,
        val xxxl: Dp
    )

    data class Sizes(
        val image: Dp,
        val iconSmall: Dp,
        val iconMedium: Dp,
    )
}

val MobileDimens = Dimens(
    paddings = Dimens.Paddings(
        xs = 4.dp,
        sm = 8.dp,
        md = 12.dp,
        lg = 16.dp,
        xl = 20.dp,
        xxl = 24.dp,
        xxxl = 32.dp
    ),
    sizes = Dimens.Sizes(
        image = 40.dp,
        iconSmall = 12.dp,
        iconMedium = 16.dp
    )
)

val MaterialTheme.dimens
    @Composable
    get() = LocalDimens.current

internal val LocalDimens = staticCompositionLocalOf<Dimens> { error("No default impl") }
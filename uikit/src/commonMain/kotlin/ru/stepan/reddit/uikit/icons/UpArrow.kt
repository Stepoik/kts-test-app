package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.UpArrow: ImageVector
    get() {
        if (_UpArrow != null) {
            return _UpArrow!!
        }
        _UpArrow = ImageVector.Builder(
            name = "UpArrow",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(10f, 19f)
                arcToRelative(3.966f, 3.966f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3.96f, -3.962f)
                verticalLineTo(10.98f)
                horizontalLineTo(2.838f)
                arcToRelative(1.731f, 1.731f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.605f, -1.073f)
                arcToRelative(1.734f, 1.734f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.377f, -1.895f)
                lineTo(9.364f, 0.254f)
                arcToRelative(0.925f, 0.925f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.272f, 0f)
                lineToRelative(7.754f, 7.759f)
                curveToRelative(0.498f, 0.499f, 0.646f, 1.242f, 0.376f, 1.894f)
                curveToRelative(-0.27f, 0.652f, -0.9f, 1.073f, -1.605f, 1.073f)
                horizontalLineToRelative(-3.202f)
                verticalLineToRelative(4.058f)
                arcTo(3.965f, 3.965f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.999f, 19f)
                horizontalLineTo(10f)
                close()
                moveTo(2.989f, 9.179f)
                horizontalLineTo(7.84f)
                verticalLineToRelative(5.731f)
                curveToRelative(0f, 1.13f, 0.81f, 2.163f, 1.934f, 2.278f)
                arcToRelative(2.163f, 2.163f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2.386f, -2.15f)
                verticalLineTo(9.179f)
                horizontalLineToRelative(4.851f)
                lineTo(10f, 2.163f)
                lineTo(2.989f, 9.179f)
                close()
            }
        }.build()

        return _UpArrow!!
    }

@Suppress("ObjectPropertyName")
private var _UpArrow: ImageVector? = null

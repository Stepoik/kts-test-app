package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.DownArrow: ImageVector
    get() {
        if (_DownArrow != null) {
            return _DownArrow!!
        }
        _DownArrow = ImageVector.Builder(
            name = "DownArrow",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(10f, 1f)
                arcToRelative(3.966f, 3.966f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.96f, 3.962f)
                lineTo(13.96f, 9.02f)
                horizontalLineToRelative(3.202f)
                curveToRelative(0.706f, 0f, 1.335f, 0.42f, 1.605f, 1.073f)
                curveToRelative(0.27f, 0.652f, 0.122f, 1.396f, -0.377f, 1.895f)
                lineToRelative(-7.754f, 7.759f)
                arcToRelative(0.925f, 0.925f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.272f, 0f)
                lineToRelative(-7.754f, -7.76f)
                arcToRelative(1.734f, 1.734f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.376f, -1.894f)
                curveToRelative(0.27f, -0.652f, 0.9f, -1.073f, 1.605f, -1.073f)
                horizontalLineToRelative(3.202f)
                lineTo(6.041f, 4.962f)
                arcTo(3.965f, 3.965f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 1f)
                close()
                moveTo(17.01f, 10.82f)
                horizontalLineToRelative(-4.85f)
                lineTo(12.16f, 5.09f)
                curveToRelative(0f, -1.13f, -0.81f, -2.163f, -1.934f, -2.278f)
                arcToRelative(2.163f, 2.163f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.386f, 2.15f)
                verticalLineToRelative(5.859f)
                lineTo(2.989f, 10.821f)
                lineToRelative(7.01f, 7.016f)
                lineToRelative(7.012f, -7.016f)
                close()
            }
        }.build()

        return _DownArrow!!
    }

@Suppress("ObjectPropertyName")
private var _DownArrow: ImageVector? = null

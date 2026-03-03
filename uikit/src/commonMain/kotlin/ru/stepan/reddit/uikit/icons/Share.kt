package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Share: ImageVector
    get() {
        if (_Share != null) {
            return _Share!!
        }
        _Share = ImageVector.Builder(
            name = "Share",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12.8f, 17.524f)
                lineToRelative(6.89f, -6.887f)
                arcToRelative(0.9f, 0.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -1.273f)
                lineTo(12.8f, 2.477f)
                arcToRelative(1.64f, 1.64f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.782f, -0.349f)
                arcToRelative(1.64f, 1.64f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.014f, 1.518f)
                verticalLineToRelative(2.593f)
                curveTo(4.054f, 6.728f, 1.192f, 12.075f, 1f, 17.376f)
                arcToRelative(1.353f, 1.353f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.862f, 1.32f)
                arcToRelative(1.35f, 1.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.531f, -0.364f)
                lineToRelative(0.334f, -0.381f)
                curveToRelative(1.705f, -1.944f, 3.323f, -3.791f, 6.277f, -4.103f)
                verticalLineToRelative(2.509f)
                curveToRelative(0f, 0.667f, 0.398f, 1.262f, 1.014f, 1.518f)
                arcToRelative(1.638f, 1.638f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.783f, -0.349f)
                verticalLineToRelative(-0.002f)
                close()
                moveTo(11.806f, 15.976f)
                lineTo(11.806f, 12f)
                horizontalLineToRelative(-0.9f)
                curveToRelative(-3.969f, 0f, -6.162f, 2.1f, -8.001f, 4.161f)
                curveToRelative(0.514f, -4.011f, 2.823f, -8.16f, 8f, -8.16f)
                horizontalLineToRelative(0.9f)
                lineTo(11.805f, 4.024f)
                lineTo(17.784f, 10f)
                lineToRelative(-5.977f, 5.976f)
                close()
            }
        }.build()

        return _Share!!
    }

@Suppress("ObjectPropertyName")
private var _Share: ImageVector? = null

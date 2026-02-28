package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Comment: ImageVector
    get() {
        if (_Comment != null) {
            return _Comment!!
        }
        _Comment = ImageVector.Builder(
            name = "Comment",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(10f, 1f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = false, isPositiveArc = false, -9f, 9f)
                curveToRelative(0f, 1.947f, 0.79f, 3.58f, 1.935f, 4.957f)
                lineTo(0.231f, 17.661f)
                arcTo(0.784f, 0.784f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.785f, 19f)
                lineTo(10f, 19f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9f, -9f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = false, isPositiveArc = false, -9f, -9f)
                close()
                moveTo(10f, 17.2f)
                lineTo(6.162f, 17.2f)
                curveToRelative(-0.994f, 0.004f, -1.907f, 0.053f, -3.045f, 0.144f)
                lineToRelative(-0.076f, -0.188f)
                arcToRelative(36.981f, 36.981f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2.328f, -2.087f)
                lineToRelative(-1.05f, -1.263f)
                curveTo(3.297f, 12.576f, 2.8f, 11.331f, 2.8f, 10f)
                curveToRelative(0f, -3.97f, 3.23f, -7.2f, 7.2f, -7.2f)
                reflectiveCurveToRelative(7.2f, 3.23f, 7.2f, 7.2f)
                reflectiveCurveToRelative(-3.23f, 7.2f, -7.2f, 7.2f)
                close()
            }
        }.build()

        return _Comment!!
    }

@Suppress("ObjectPropertyName")
private var _Comment: ImageVector? = null

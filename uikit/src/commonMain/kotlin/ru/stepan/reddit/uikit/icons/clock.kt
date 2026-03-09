package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Clock: ImageVector
    get() {
        if (_Clock != null) {
            return _Clock!!
        }
        _Clock = ImageVector.Builder(
            name = "Clock",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.99f, 0f)
                curveTo(15.52f, 0f, 20f, 4.48f, 20f, 10f)
                reflectiveCurveTo(15.52f, 20f, 9.99f, 20f)
                curveTo(4.47f, 20f, 0f, 15.52f, 0f, 10f)
                reflectiveCurveTo(4.47f, 0f, 9.99f, 0f)
                close()
                moveTo(10f, 18f)
                curveToRelative(4.42f, 0f, 8f, -3.58f, 8f, -8f)
                reflectiveCurveToRelative(-3.58f, -8f, -8f, -8f)
                reflectiveCurveToRelative(-8f, 3.58f, -8f, 8f)
                reflectiveCurveToRelative(3.58f, 8f, 8f, 8f)
                close()
            }
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.66f, 9.24f)
                lineToRelative(-1.41f, 1.41f)
                lineTo(6f, 6.41f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, 7.41f, 5f)
                lineToRelative(4.24f, 4.24f)
                close()
            }
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.31f, 10.73f)
                lineTo(8.9f, 9.31f)
                lineToRelative(3.54f, -3.54f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, 1.41f, 1.41f)
                lineToRelative(-3.54f, 3.54f)
                close()
            }
        }.build()

        return _Clock!!
    }

@Suppress("ObjectPropertyName")
private var _Clock: ImageVector? = null

package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Check: ImageVector
    get() {
        if (_Check != null) {
            return _Check!!
        }
        _Check = ImageVector.Builder(
            name = "Check",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF66CC66)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(7.644f, 13.469f)
                lineToRelative(-2.892f, -2.892f)
                arcToRelative(0.83f, 0.83f, 0f, isMoreThanHalf = true, isPositiveArc = false, -1.175f, 1.175f)
                lineToRelative(3.483f, 3.483f)
                arcToRelative(0.83f, 0.83f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.175f, 0f)
                lineToRelative(8.817f, -8.816f)
                arcToRelative(0.83f, 0.83f, 0f, isMoreThanHalf = true, isPositiveArc = false, -1.175f, -1.175f)
                lineToRelative(-8.233f, 8.225f)
                close()
            }
        }.build()

        return _Check!!
    }

@Suppress("ObjectPropertyName")
private var _Check: ImageVector? = null

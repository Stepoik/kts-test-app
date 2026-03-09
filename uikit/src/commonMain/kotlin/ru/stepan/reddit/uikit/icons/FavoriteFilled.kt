package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.FavoriteFilled: ImageVector
    get() {
        if (_FavoriteFilled != null) {
            return _FavoriteFilled!!
        }
        ImageVector.Builder(
            name = "FavoriteFilled",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.35f, 17.13f)
                curveToRelative(-0.76f, 0.69f, -1.93f, 0.69f, -2.69f, -0.01f)
                lineToRelative(-0.11f, -0.1f)
                curveTo(3.3f, 12.27f, -0.13f, 9.16f, 0f, 5.28f)
                curveToRelative(0.06f, -1.7f, 0.93f, -3.33f, 2.34f, -4.29f)
                curveToRelative(2.64f, -1.8f, 5.9f, -0.96f, 7.66f, 1.1f)
                curveToRelative(1.76f, -2.06f, 5.02f, -2.91f, 7.66f, -1.1f)
                curveToRelative(1.41f, 0.96f, 2.28f, 2.59f, 2.34f, 4.29f)
                curveToRelative(0.14f, 3.88f, -3.3f, 6.99f, -8.55f, 11.76f)
                lineToRelative(-0.1f, 0.09f)
                close()
            }
        }.build().also { _FavoriteFilled = it }

        return _FavoriteFilled!!
    }

@Suppress("ObjectPropertyName")
private var _FavoriteFilled: ImageVector? = null

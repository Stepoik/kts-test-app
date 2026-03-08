package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Person: ImageVector
    get() {
        if (_Person != null) {
            return _Person!!
        }
        _Person = ImageVector.Builder(
            name = "Person",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.62f, 12.13f)
                curveToRelative(-0.05f, -0.58f, -0.03f, -0.99f, -0.03f, -1.51f)
                curveToRelative(0.26f, -0.14f, 0.73f, -1.02f, 0.81f, -1.76f)
                curveToRelative(0.21f, -0.02f, 0.53f, -0.22f, 0.63f, -1.01f)
                curveToRelative(0.05f, -0.43f, -0.15f, -0.67f, -0.28f, -0.74f)
                curveToRelative(0.34f, -1.01f, 1.03f, -4.14f, -1.29f, -4.46f)
                curveTo(10.22f, 2.21f, 9.6f, 2f, 8.81f, 2f)
                curveToRelative(-3.19f, 0.06f, -3.57f, 2.41f, -2.87f, 5.1f)
                curveToRelative(-0.12f, 0.08f, -0.33f, 0.32f, -0.28f, 0.74f)
                curveToRelative(0.1f, 0.8f, 0.42f, 1f, 0.63f, 1.01f)
                curveToRelative(0.08f, 0.74f, 0.57f, 1.62f, 0.83f, 1.76f)
                curveToRelative(0f, 0.53f, 0.02f, 0.94f, -0.03f, 1.51f)
                curveToRelative(-0.63f, 1.7f, -4.89f, 1.22f, -5.08f, 4.49f)
                horizontalLineToRelative(13.68f)
                curveToRelative(-0.2f, -3.27f, -4.43f, -2.79f, -5.06f, -4.49f)
                close()
            }
        }.build()

        return _Person!!
    }

@Suppress("ObjectPropertyName")
private var _Person: ImageVector? = null

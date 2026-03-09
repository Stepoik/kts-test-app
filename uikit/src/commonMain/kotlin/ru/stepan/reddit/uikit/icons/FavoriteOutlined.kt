package ru.stepan.reddit.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.FavoriteOutlined: ImageVector
    get() {
        if (_FavoriteOutlined != null) {
            return _FavoriteOutlined!!
        }
        _FavoriteOutlined = ImageVector.Builder(
            name = "FavoriteOutlined",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(17.66f, 0.99f)
                curveToRelative(1.41f, 0.96f, 2.28f, 2.59f, 2.34f, 4.29f)
                curveToRelative(0.09f, 2.56f, -1.37f, 4.78f, -3.89f, 7.37f)
                lineToRelative(-0.5f, 0.51f)
                lineToRelative(-0.26f, 0.26f)
                lineToRelative(-0.54f, 0.52f)
                lineToRelative(-0.85f, 0.8f)
                lineToRelative(-1.21f, 1.12f)
                lineToRelative(-1.4f, 1.27f)
                curveToRelative(-0.76f, 0.69f, -1.93f, 0.69f, -2.69f, -0.01f)
                lineToRelative(-1.44f, -1.3f)
                lineToRelative(-0.63f, -0.58f)
                lineToRelative(-0.61f, -0.56f)
                lineToRelative(-0.58f, -0.55f)
                lineToRelative(-0.56f, -0.54f)
                lineToRelative(-0.27f, -0.26f)
                lineToRelative(-0.52f, -0.52f)
                lineToRelative(-0.49f, -0.51f)
                curveTo(1.25f, 9.87f, -0.08f, 7.73f, 0f, 5.28f)
                curveToRelative(0.06f, -1.7f, 0.93f, -3.33f, 2.34f, -4.29f)
                curveToRelative(2.64f, -1.8f, 5.9f, -0.96f, 7.66f, 1.1f)
                curveToRelative(1.76f, -2.06f, 5.02f, -2.91f, 7.66f, -1.1f)
                close()
                moveTo(16.53f, 2.64f)
                curveToRelative(-1.55f, -1.06f, -3.6f, -0.74f, -4.87f, 0.6f)
                lineToRelative(-0.14f, 0.15f)
                lineToRelative(-1.52f, 1.78f)
                lineToRelative(-1.52f, -1.78f)
                curveToRelative(-1.25f, -1.47f, -3.4f, -1.85f, -5.01f, -0.75f)
                curveToRelative(-0.87f, 0.59f, -1.43f, 1.63f, -1.47f, 2.7f)
                arcTo(5.34f, 5.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 5.6f)
                lineToRelative(0.01f, 0.25f)
                curveToRelative(0.02f, 0.25f, 0.05f, 0.5f, 0.11f, 0.74f)
                lineToRelative(0.07f, 0.25f)
                curveToRelative(0.08f, 0.25f, 0.17f, 0.5f, 0.3f, 0.77f)
                lineToRelative(0.14f, 0.27f)
                curveToRelative(0.02f, 0.05f, 0.05f, 0.09f, 0.08f, 0.14f)
                lineToRelative(0.17f, 0.28f)
                lineToRelative(0.19f, 0.29f)
                lineToRelative(0.1f, 0.15f)
                lineToRelative(0.22f, 0.3f)
                lineToRelative(0.12f, 0.16f)
                lineToRelative(0.26f, 0.32f)
                lineToRelative(0.28f, 0.33f)
                lineToRelative(0.3f, 0.35f)
                lineToRelative(0.33f, 0.36f)
                lineToRelative(0.36f, 0.38f)
                lineToRelative(0.38f, 0.4f)
                lineToRelative(0.41f, 0.41f)
                lineToRelative(0.44f, 0.43f)
                lineToRelative(0.72f, 0.69f)
                lineToRelative(0.79f, 0.74f)
                lineToRelative(0.86f, 0.8f)
                lineToRelative(1.38f, 1.26f)
                lineToRelative(1.84f, -1.68f)
                lineToRelative(1.21f, -1.13f)
                lineToRelative(0.83f, -0.8f)
                lineToRelative(0.55f, -0.54f)
                lineToRelative(0.49f, -0.5f)
                lineToRelative(0.44f, -0.46f)
                lineToRelative(0.26f, -0.29f)
                lineToRelative(0.24f, -0.28f)
                lineToRelative(0.33f, -0.4f)
                lineToRelative(0.2f, -0.25f)
                lineToRelative(0.19f, -0.25f)
                lineToRelative(0.17f, -0.24f)
                lineToRelative(0.16f, -0.24f)
                lineToRelative(0.15f, -0.24f)
                lineToRelative(0.14f, -0.24f)
                lineToRelative(0.13f, -0.24f)
                curveToRelative(0.48f, -0.89f, 0.68f, -1.69f, 0.65f, -2.52f)
                arcToRelative(3.48f, 3.48f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.3f, -2.58f)
                lineToRelative(-0.17f, -0.13f)
                close()
            }
        }.build()

        return _FavoriteOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _FavoriteOutlined: ImageVector? = null

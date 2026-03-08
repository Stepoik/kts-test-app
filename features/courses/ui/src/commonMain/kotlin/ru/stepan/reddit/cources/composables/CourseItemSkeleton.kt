package ru.stepan.reddit.cources.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.stepan.reddit.uikit.components.HorizontalSpacer
import ru.stepan.reddit.uikit.components.VerticalSpacer
import ru.stepan.reddit.uikit.dimens

@Composable
fun CourseItemSkeleton(modifier: Modifier = Modifier) {
    Row(modifier) {
        SkeletonBox(
            modifier = Modifier
                .size(MaterialTheme.dimens.sizes.image)
                .clip(MaterialTheme.shapes.extraSmall)
        )

        HorizontalSpacer(MaterialTheme.dimens.paddings.md)

        Column(Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CourseInfoSkeleton(
                    modifier = Modifier.weight(1f)
                )
                LikeButtonSkeleton()
            }

            VerticalSpacer(MaterialTheme.dimens.paddings.xs)
            HorizontalDivider(Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun CourseInfoSkeleton(modifier: Modifier = Modifier) {
    Column(modifier) {
        SkeletonLine(
            width = 0.75f,
            height = 20.dp
        )

        VerticalSpacer(MaterialTheme.dimens.paddings.xs)

        SkeletonLine(
            width = 0.5f,
            height = 14.dp
        )

        VerticalSpacer(MaterialTheme.dimens.paddings.md)

        PricesSkeleton()

        VerticalSpacer(MaterialTheme.dimens.paddings.sm)

        ReviewSkeleton()
    }
}

@Composable
private fun PricesSkeleton() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        SkeletonLine(
            width = 0.2f,
            height = 18.dp
        )
    }
}

@Composable
private fun ReviewSkeleton() {
    val iconSize = MaterialTheme.dimens.sizes.iconSmall

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.paddings.md),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.paddings.xs)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(5) {
                SkeletonBox(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                )
                HorizontalSpacer(2.dp)
            }

            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)

            SkeletonLine(width = 36.dp, height = 12.dp)
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            SkeletonLine(width = 42.dp, height = 12.dp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            SkeletonBox(
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape)
            )
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            SkeletonLine(width = 56.dp, height = 12.dp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            SkeletonBox(
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape)
            )
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            SkeletonLine(width = 44.dp, height = 12.dp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            SkeletonBox(
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape)
            )
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            SkeletonLine(width = 72.dp, height = 12.dp)
        }
    }
}

@Composable
private fun LikeButtonSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        SkeletonBox(
            modifier = Modifier
                .size(MaterialTheme.dimens.sizes.iconMedium)
                .clip(CircleShape)
        )
    }
}

@Composable
private fun SkeletonLine(
    width: Float,
    height: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(width)
            .height(height)
            .clip(RoundedCornerShape(6.dp))
            .background(skeletonColor())
    )
}

@Composable
private fun SkeletonLine(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(6.dp))
            .background(skeletonColor())
    )
}

@Composable
private fun SkeletonBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(skeletonColor())
    )
}

@Composable
private fun skeletonColor(): Color {
    return MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
}
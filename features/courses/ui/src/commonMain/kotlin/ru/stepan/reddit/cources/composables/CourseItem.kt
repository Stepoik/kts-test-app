package ru.stepan.reddit.cources.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.uikit.RedditTheme
import ru.stepan.reddit.uikit.components.HorizontalSpacer
import ru.stepan.reddit.uikit.components.VerticalSpacer
import ru.stepan.reddit.uikit.dimens
import ru.stepan.reddit.uikit.icons.Certificate
import ru.stepan.reddit.uikit.icons.Clock
import ru.stepan.reddit.uikit.icons.FavoriteFilled
import ru.stepan.reddit.uikit.icons.FavoriteOutlined
import ru.stepan.reddit.uikit.icons.Icons
import ru.stepan.reddit.uikit.icons.Person
import testapp.features.courses.ui.generated.resources.Res
import testapp.features.courses.ui.generated.resources.certificate
import testapp.features.courses.ui.generated.resources.value_hours

@Composable
fun CourseItem(course: CourseVO, onLike: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier) {
        AsyncImage(
            course.cover,
            contentDescription = course.title,
            modifier = Modifier
                .size(MaterialTheme.dimens.sizes.image)
                .clip(MaterialTheme.shapes.extraSmall)
        )
        HorizontalSpacer(MaterialTheme.dimens.paddings.md)
        Column(Modifier.weight(1f)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CourseInfo(course, modifier = Modifier.weight(1f))
                LikeButton(course.isLiked, onClick = onLike)
            }
            VerticalSpacer(MaterialTheme.dimens.paddings.xs)
            HorizontalDivider(Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun CourseInfo(course: CourseVO, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            course.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(course.authors, style = MaterialTheme.typography.bodySmall)
        VerticalSpacer(MaterialTheme.dimens.paddings.md)
        Prices(course)
        Review(course.review)
    }
}

@Composable
private fun Review(review: CourseReview) {
    val iconsModifier = Modifier.size(MaterialTheme.dimens.sizes.iconSmall)
    val contentColors = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    val fontStyle = MaterialTheme.typography.bodySmall
    FlowRow(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.paddings.md)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Stars(review.starsCount)
            Text(review.average, color = contentColors, style = fontStyle)
            Text("(${review.votesCount})", color = contentColors, style = fontStyle)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Person,
                contentDescription = review.learnersCount,
                modifier = iconsModifier,
                tint = contentColors
            )
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            Text(review.learnersCount, color = contentColors, style = fontStyle)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Clock,
                contentDescription = stringResource(Res.string.value_hours, review.timeToComplete),
                modifier = iconsModifier,
                tint = contentColors
            )
            HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
            Text(
                stringResource(Res.string.value_hours, review.timeToComplete),
                color = contentColors,
                style = fontStyle
            )
        }
        if (review.withCertificate) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Certificate,
                    contentDescription = stringResource(Res.string.certificate),
                    modifier = iconsModifier,
                    tint = contentColors
                )
                HorizontalSpacer(MaterialTheme.dimens.paddings.xs)
                Text(
                    stringResource(Res.string.certificate),
                    color = contentColors,
                    style = fontStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun Stars(starsCount: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) {
            if (it + 1 <= starsCount) {
                Text(
                    "★",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            } else {
                Box(Modifier.width(IntrinsicSize.Max)) {
                    Text(
                        "★",
                        style = MaterialTheme.typography.bodySmall
                    )
                    val fraction = 1 - ((it + 1 - starsCount)).toFloat().coerceIn(0f, 1f)
                    Box(Modifier.fillMaxWidth(fraction).clipToBounds()) {
                        Text(
                            "★",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Prices(course: CourseVO) {
    Row {
        Text(course.displayPrice)
    }
}

@Composable
private fun LikeButton(isLiked: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val icon = if (isLiked) {
        Icons.FavoriteFilled
    } else {
        Icons.FavoriteOutlined
    }
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            icon,
            contentDescription = "like",
            modifier = Modifier.size(MaterialTheme.dimens.sizes.iconMedium)
        )
    }
}

data class CourseVO(
    val id: Long,
    val cover: String,
    val title: String,
    val authors: String,
    val isLiked: Boolean,
    val displayPrice: String,
    val displayDiscountPrice: String?,
    val review: CourseReview
)

data class CourseReview(
    val average: String,
    val votesCount: String,
    val learnersCount: String,
    val starsCount: Double,
    val timeToComplete: Int,
    val withCertificate: Boolean
)

@Composable
@Preview
private fun CourseItemPreview() {
    RedditTheme {
        CourseItem(
            course = CourseVO(
                id = 1,
                cover = "",
                title = "PRO Kotlin",
                authors = "JetBrains",
                isLiked = true,
                displayPrice = "1000 Р",
                displayDiscountPrice = null,
                review = CourseReview(
                    average = "4.8",
                    votesCount = "64",
                    learnersCount = "1.7К",
                    starsCount = 4.4,
                    timeToComplete = 60,
                    withCertificate = true
                )
            ),
            onLike = {},
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
        )
    }
}
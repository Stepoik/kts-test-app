package ru.stepan.reddit.course.details.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.charlex.compose.htmltext.material.HtmlText
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.uikit.components.VerticalSpacer
import ru.stepan.reddit.uikit.dimens
import ru.stepan.reddit.uikit.icons.Check
import ru.stepan.reddit.uikit.icons.Icons
import testapp.features.course_details.ui.generated.resources.Res
import testapp.features.course_details.ui.generated.resources.about_course
import testapp.features.course_details.ui.generated.resources.acquired_skills

@Composable
fun CourseDetailsScreen(component: CourseDetailsComponent) {
    val state = component.state.collectAsState().value

    Scaffold { innerPadding ->
        if (state.course != null) {
            CourseInfo(
                course = state.course,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding.calculateBottomPadding())
            )
        }
    }
}

@Composable
private fun CourseInfo(course: FullCourseVO, modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        SummaryBlock(course, modifier = Modifier.fillMaxWidth())
        VerticalSpacer(MaterialTheme.dimens.paddings.xl)
        AcquiredSkillsBlock(course.acquiredSkills)
        VerticalSpacer(MaterialTheme.dimens.paddings.xxxl)
        AboutBlock(course.description)
    }
}

@Composable
private fun SummaryBlock(course: FullCourseVO, modifier: Modifier = Modifier) {
    val backgroundColor = Color(0xFF282B41)
    Column(
        modifier.background(backgroundColor)
            .statusBarsPadding()
            .padding(top = 70.dp, bottom = 48.dp)
            .padding(horizontal = 20.dp)
    ) {
        val textColor = MaterialTheme.colorScheme.onPrimary
        Text(course.title, style = MaterialTheme.typography.titleLarge, color = textColor)
        VerticalSpacer(MaterialTheme.dimens.paddings.lg)
        Text(course.summary, style = MaterialTheme.typography.bodyLarge, color = textColor)
    }
}

@Composable
private fun AcquiredSkillsBlock(acquiredSkills: List<String>, modifier: Modifier = Modifier) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Text(
            stringResource(Res.string.acquired_skills),
            style = MaterialTheme.typography.titleLarge
        )
        acquiredSkills.forEach {
            Row {
                Icon(
                    Icons.Check,
                    contentDescription = it,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(MaterialTheme.dimens.sizes.iconMedium)
                )
                Text(it, color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Composable
private fun AboutBlock(description: String, modifier: Modifier = Modifier) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Text(stringResource(Res.string.about_course), style = MaterialTheme.typography.titleLarge)
        VerticalSpacer(MaterialTheme.dimens.paddings.lg)
        HtmlText(text = description)
    }
}
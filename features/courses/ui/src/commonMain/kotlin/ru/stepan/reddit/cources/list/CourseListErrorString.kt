package ru.stepan.reddit.cources.list

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import testapp.features.courses.ui.generated.resources.Res
import testapp.features.courses.ui.generated.resources.network_error
import testapp.features.courses.ui.generated.resources.unknown_error

@Composable
fun CourseListError.toText(): String {
    return when (this) {
        CourseListError.NETWORK -> stringResource(Res.string.network_error)
        CourseListError.UNKNOWN -> stringResource(Res.string.unknown_error)
    }
}
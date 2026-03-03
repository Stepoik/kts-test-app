package ru.stepan.reddit

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.root.entry.RootEntryComponent
import ru.stepan.reddit.uikit.RedditTheme

@Composable
fun App(rootComponent: RootEntryComponent) {
    RedditTheme {
        rootComponent.Render()
    }
}
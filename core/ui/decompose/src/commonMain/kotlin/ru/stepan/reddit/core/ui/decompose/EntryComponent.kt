package ru.stepan.reddit.core.ui.decompose

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

abstract class EntryComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, DecomposeComponent() {
    @Composable
    abstract fun Render()
}
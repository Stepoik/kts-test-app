package ru.stepan.reddit.core.ui.decompose

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ScreenComponent<S, E> {
    val state: StateFlow<S>

    val events: SharedFlow<E>
}
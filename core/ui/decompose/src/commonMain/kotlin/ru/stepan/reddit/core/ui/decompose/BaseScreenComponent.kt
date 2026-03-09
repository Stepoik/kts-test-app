package ru.stepan.reddit.core.ui.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.KSerializer
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseScreenComponent<S: Any, E>(
    componentContext: ComponentContext,
    initialState: S,
    private val serializer: KSerializer<S>
) : ComponentContext by componentContext, ScreenComponent<S, E>, DecomposeComponent() {
    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<E>(replay = 1)
    override val events: SharedFlow<E> = _events

    init {
        doOnDestroy {
            componentScope.cancel()
        }

        stateKeeper.consume(STATE_SAVER, serializer)?.let { oldState ->
            updateState { oldState }
        }
        stateKeeper.register(STATE_SAVER, serializer) {
            onSaveState(_state.value)
        }
    }

    protected fun updateState(function: (S) -> S) {
        _state.update { function(it) }
    }

    protected fun sendEvent(event: E) {
        _events.tryEmit(event)
    }

    open fun onSaveState(state: S): S {
        return state
    }

    companion object {
        private const val STATE_SAVER = "state"
    }
}
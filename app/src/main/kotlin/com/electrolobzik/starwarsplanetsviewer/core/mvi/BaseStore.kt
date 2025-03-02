package com.electrolobzik.starwarsplanetsviewer.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseStore<Intent, Effect, State, OneTimeMessage, NavigationEvent>(
    coroutineContext: CoroutineContext
) : CoroutineScope by CoroutineScope(coroutineContext + SupervisorJob()) {

    abstract val state: MutableStateFlow<State>
    val stateFlow get() = state.asStateFlow()

    private val effectsFlow = MutableSharedFlow<Effect>()

    private val navigationEvents = Channel<NavigationEvent>()
    val navigationEventsFlow get() = navigationEvents.receiveAsFlow()

    private val oneTimeMessages = Channel<OneTimeMessage>()
    val oneTimeMessagesFlow get() = oneTimeMessages.receiveAsFlow()

    init {
        launch(Dispatchers.Default) {
            effectsFlow.collect { effect ->
                val newState = reduce(state.value, effect)
                state.emit(newState)
            }
        }
    }

    abstract fun acceptIntent(intent: Intent)

    protected fun emitEffect(effect: Effect) {
        launch(Dispatchers.Default) {
            effectsFlow.emit(effect)
        }
    }

    protected fun emitOneTimeMessage(message: OneTimeMessage) {
        launch(Dispatchers.Default) {
            oneTimeMessages.send(message)
        }
    }

    protected fun emitNavigationEvent(event: NavigationEvent) {
        launch {
            navigationEvents.send(event)
        }
    }

    abstract fun reduce(state: State, effect: Effect): State
}
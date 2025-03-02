package com.electrolobzik.starwarsplanetsviewer.core.mvi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseStore<Intent, Effect, State, OneTimeMessage, NavigationEvent>(
    private val defaultDispatcher: CoroutineDispatcher
) : CoroutineScope by CoroutineScope(defaultDispatcher + SupervisorJob()) {

    abstract val state: MutableStateFlow<State>
    val stateFlow get() = state.asStateFlow()

    private val effectsFlow = MutableSharedFlow<Effect>()

    private val navigationEvents = Channel<NavigationEvent>()
    val navigationEventsFlow get() = navigationEvents.receiveAsFlow()

    private val oneTimeMessages = Channel<OneTimeMessage>()
    val oneTimeMessagesFlow get() = oneTimeMessages.receiveAsFlow()

    init {
        launch {
            effectsFlow.collect { effect ->
                val newState = reduce(state.value, effect)
                state.emit(newState)
            }
        }
    }

    abstract fun acceptIntent(intent: Intent)

    protected fun emitEffect(effect: Effect) {
        launch {
            effectsFlow.emit(effect)
        }
    }

    protected fun emitOneTimeMessage(message: OneTimeMessage) {
        launch {
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
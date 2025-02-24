package com.electrolobzik.starwarsplanetsviewer.core.mvi

sealed interface OneTimeEvent {
    data object NoConnection : OneTimeEvent
    data class UnknownError(val error: Throwable) : OneTimeEvent
}

fun OneTimeEvent.UnknownError.getDescription(): String {
    return "Unknown error: ${error.message ?: "Unknown error"}"
}
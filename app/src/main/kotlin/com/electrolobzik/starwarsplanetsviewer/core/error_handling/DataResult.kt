package com.electrolobzik.starwarsplanetsviewer.core.error_handling

sealed interface DataResult<Data> {
    val success: Boolean
    val actionDescription: String

    data class Success<Data>(val data: Data, override val actionDescription: String) : DataResult<Data> {
        override val success = true
    }

    data class ConnectionIssue<Data>(override val actionDescription: String) : DataResult<Data> {
        override val success = false
    }

    data class UnknownError<Data>(
        val unknownError: Throwable,
        override val actionDescription: String
    ) : DataResult<Data> {
        override val success = false
    }

    val isSuccess: Boolean get() = success

    val isFailure: Boolean get() = !success

    val isErrorUnknown: Boolean get() = this is UnknownError
}
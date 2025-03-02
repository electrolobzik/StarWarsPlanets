package com.electrolobzik.starwarsplanetsviewer.core.error_handling

import com.electrolobzik.starwarsplanetsviewer.core.utils.ConnectionChecker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import okio.IOException

suspend inline fun <reified Data> runRequest(
    connectionChecker: ConnectionChecker,
    actionDescription: String,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline block: suspend () -> Data
): DataResult<Data> {
    return withContext(dispatcher) {
        try {
            if (connectionChecker.isNetworkAvailable()) {
                val resultData = block()
                DataResult.Success(data = resultData, actionDescription = actionDescription)
            } else {
                DataResult.ConnectionIssue(actionDescription)
            }

        } catch (throwable: Throwable) {
            // check if the coroutine is still active and rethrow CancellationException if not
            ensureActive()

            when (throwable) {
                is IOException -> DataResult.ConnectionIssue(actionDescription)
                else -> DataResult.UnknownError(
                    unknownError = throwable,
                    actionDescription = actionDescription
                )
            }
        }
    }
}

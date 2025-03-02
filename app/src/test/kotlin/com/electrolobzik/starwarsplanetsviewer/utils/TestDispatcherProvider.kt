package com.electrolobzik.starwarsplanetsviewer.utils

import com.electrolobzik.starwarsplanetsviewer.core.coroutines.DispatcherProvider
import kotlinx.coroutines.test.TestDispatcher

class TestDispatcherProvider(
    private val testDispatcher: TestDispatcher
) : DispatcherProvider {
    override val main get() = testDispatcher
    override val io get() = testDispatcher
    override val default get() = testDispatcher
    override val unconfined get() = testDispatcher
}
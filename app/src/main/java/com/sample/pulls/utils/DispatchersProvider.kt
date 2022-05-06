package com.sample.pulls.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Class to delegate CoroutineDispatchers.
 */
interface DispatchersProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}

package com.sample.pulls.utils

import kotlinx.coroutines.Dispatchers

/**
 * Default dispatcher provider.
 * Provides Io dispatcher to handle background work.
 * Provides main dispatcher to handle Main Thread Operations and UI Objects.
 */
class DefaultDispatchersProvider : DispatchersProvider {

    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main
}

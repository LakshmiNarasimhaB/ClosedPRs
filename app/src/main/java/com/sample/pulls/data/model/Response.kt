package com.sample.pulls.data.model

import java.io.IOException

/**
 * Wrapper Response class to emit and wrap remote api data responses.
 */
sealed class Response<out T> {

    data class Success<T>(val data: T) : Response<T>()

    data class Error(val throwable: Throwable) : Response<Nothing>() {
        fun isNetworkException() = throwable is IOException
    }

    object Empty : Response<Nothing>()

    object Loading : Response<Nothing>()

    companion object {
        /**
         * Return Success.
         */
        fun <T> success(data: T) = Success(data)

        /**
         * Return Error.
         */
        fun error(throwable: Throwable) = Error(throwable)

        /**
         * Return Empty.
         */
        fun empty() = Empty

        /**
         * Return Loading.
         */
        fun loading() = Loading

        /**
         * Return Success or Empty.
         */
        fun <T> successOrEmpty(list: List<T>): Response<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}

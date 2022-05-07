package com.sample.pulls.data.remote

import com.sample.pulls.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Header interceptor to pass bearer token to every request.
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization:token", BuildConfig.API_KEY)
                .build()
        )
    }
}

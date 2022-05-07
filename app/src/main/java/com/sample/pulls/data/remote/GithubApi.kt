package com.sample.pulls.data.remote

import android.content.Context
import com.sample.pulls.BuildConfig
import com.sample.pulls.data.model.PullRequestData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Retrofit Interface to access Github REST API.
 */
interface GithubApi {

    @GET("repos/${OWNER}/${REPO}/pulls")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getClosedPullRequests(
        @Query("state") state: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<PullRequestData>

    companion object {
        fun createGitHubApi(context: Context): GithubApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(createGsonConverterFactory())
                .build()
                .create(GithubApi::class.java)
        }

        private fun createGsonConverterFactory() = GsonConverterFactory.create()

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(createHttpLoggingInterceptor())
                .build()
        }

        /**
         * Logging interceptor to log Network Requests and Responses.
         * Only enable logging when build is of type DEBUG.
         */
        private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    }
}

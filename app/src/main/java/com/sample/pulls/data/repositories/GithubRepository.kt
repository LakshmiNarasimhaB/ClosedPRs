package com.sample.pulls.data.repositories

import com.sample.pulls.data.model.Response
import com.sample.pulls.domain.model.PullRequest
import kotlinx.coroutines.flow.Flow

/**
 * Repository class.
 */
interface GithubRepository {
    suspend fun getPullRequests(): Flow<Response<List<PullRequest>>>
}

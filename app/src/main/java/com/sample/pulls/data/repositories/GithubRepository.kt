package com.sample.pulls.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sample.pulls.data.model.Response
import com.sample.pulls.domain.model.PullRequest
import kotlinx.coroutines.flow.Flow

/**
 * Repository class.
 */
interface GithubRepository {
    fun getPullRequests(state: String): LiveData<PagingData<PullRequest>>
}

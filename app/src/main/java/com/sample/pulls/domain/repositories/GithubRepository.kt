package com.sample.pulls.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sample.pulls.domain.model.PullRequest

/**
 * Repository class.
 */
interface GithubRepository {
    fun getPullRequests(state: String): LiveData<PagingData<PullRequest>>
}

package com.sample.pulls.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.sample.pulls.data.mappers.PullRequestMapper
import com.sample.pulls.data.remote.GithubApi
import com.sample.pulls.data.remote.GithubPagingSource
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.domain.repositories.GithubRepository

/**
 * Github Repository implementation.
 */
class PullRequestsGithubRepository(
    private val pullRequestMapper: PullRequestMapper,
    private val githubApi: GithubApi
) : GithubRepository {

    override fun getPullRequests(state: String): LiveData<PagingData<PullRequest>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { GithubPagingSource(githubApi, state, pullRequestMapper) }
        ).liveData
    }
}

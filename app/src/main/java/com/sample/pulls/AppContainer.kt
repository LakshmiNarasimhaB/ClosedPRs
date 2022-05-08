package com.sample.pulls

import com.sample.pulls.data.mappers.PullRequestMapper
import com.sample.pulls.data.remote.GithubApi
import com.sample.pulls.domain.repositories.GithubRepository
import com.sample.pulls.data.repositories.PullRequestsGithubRepository
import com.sample.pulls.presentation.pullslist.viewmodel.PullRequestListViewModelFactory

/**
 * Container class for app dependencies.
 */
class AppContainer {

    init {
        val repository = createRepository()
        PullRequestListViewModelFactory.inject(repository)
    }

    private fun createRepository(): GithubRepository {
        return PullRequestsGithubRepository(
            PullRequestMapper(),
            GithubApi.createGitHubApi()
        )
    }
}

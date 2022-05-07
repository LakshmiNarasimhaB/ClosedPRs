package com.sample.pulls

import android.app.Application
import com.sample.pulls.data.mappers.PullRequestMapper
import com.sample.pulls.data.remote.GithubApi
import com.sample.pulls.data.repositories.GithubRepository
import com.sample.pulls.data.repositories.PullRequestsGithubRepository
import com.sample.pulls.presentation.pullslist.PullRequestListViewModelFactory
import com.sample.pulls.utils.DefaultDispatchersProvider

class SampleRepositoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val repository = createRepository()
        PullRequestListViewModelFactory.inject(repository)
    }

    private fun createRepository(): GithubRepository {
        return PullRequestsGithubRepository(
            DefaultDispatchersProvider(),
            PullRequestMapper(),
            GithubApi.createGitHubApi(this)
        )
    }
}

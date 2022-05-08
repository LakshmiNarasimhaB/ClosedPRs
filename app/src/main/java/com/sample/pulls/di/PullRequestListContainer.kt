package com.sample.pulls.di

import com.sample.pulls.domain.repositories.GithubRepository
import com.sample.pulls.presentation.pullslist.viewmodel.PullRequestListViewModelFactory

class PullRequestListContainer(githubRepository: GithubRepository) {
    init {
        PullRequestListViewModelFactory.inject(githubRepository)
    }
}

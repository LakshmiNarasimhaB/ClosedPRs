@file:Suppress("UNCHECKED_CAST")

package com.sample.pulls.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.pulls.data.repositories.GithubRepository

object PullRequestListViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repository: GithubRepository

    fun inject(repository: GithubRepository) {
        PullRequestListViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestsListViewModel(repository) as T
    }
}

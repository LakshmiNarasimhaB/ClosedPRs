package com.sample.pulls.presentation

import androidx.lifecycle.*
import com.sample.pulls.data.repositories.GithubRepository
import com.sample.pulls.domain.model.PullRequest
import kotlinx.coroutines.launch

class PullRequestsListViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val pullRequests: MutableLiveData<List<PullRequest>> by lazy {
        MutableLiveData<List<PullRequest>>().also {
            loadPullRequests()
        }
    }

    fun getPullRequests(): LiveData<List<PullRequest>> {
        return pullRequests
    }

    private fun loadPullRequests() {
        viewModelScope.launch { githubRepository.getPullRequests().asLiveData() }
    }
}

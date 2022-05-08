package com.sample.pulls.presentation.pullslist.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.sample.pulls.domain.repositories.GithubRepository
import com.sample.pulls.presentation.pullslist.model.PullState

/**
 * View model for PullRequestsListFragment.
 */
class PullRequestsListViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val currentState = MutableLiveData(PullState.CLOSED.state)

    fun getPullRequestsForState(position: Int) {
        val pullState = PullState.fromPosition(position)
        if (currentState.value != pullState) {
            currentState.value = pullState
        }
    }

    val pullRequests = currentState.switchMap { state ->
        githubRepository.getPullRequests(state).cachedIn(viewModelScope)
    }
}

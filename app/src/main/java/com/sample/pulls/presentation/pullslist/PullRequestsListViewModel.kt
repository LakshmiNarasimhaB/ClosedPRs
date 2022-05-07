package com.sample.pulls.presentation.pullslist

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.sample.pulls.data.repositories.GithubRepository
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.presentation.model.PullState
import kotlinx.coroutines.launch

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

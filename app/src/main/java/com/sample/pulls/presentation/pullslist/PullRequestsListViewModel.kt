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

    companion object {
        private const val CLOSED = "closed"
        private const val OPEN = "open"
        private const val ALL = "all"
    }

    private val currentState = MutableLiveData(CLOSED)

    fun getPullRequestsForState(position: Int) {
        currentState.value = PullState.fromPosition(position)
    }

    val pullRequests = currentState.switchMap { state ->
        githubRepository.getPullRequests(state).cachedIn(viewModelScope)
    }
}

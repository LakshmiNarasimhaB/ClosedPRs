package com.sample.pulls.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.sample.pulls.data.mappers.PullRequestMapper
import com.sample.pulls.data.model.Response
import com.sample.pulls.data.remote.GithubApi
import com.sample.pulls.data.remote.GithubPagingSource
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.utils.DefaultDispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Github Repository implementation.
 */
class PullRequestsGithubRepository(
    private val dispatchersProvider: DefaultDispatchersProvider,
    private val pullRequestMapper: PullRequestMapper,
    private val githubApi: GithubApi
) : GithubRepository {

//    override suspend fun getPullRequests(): Flow<Response<List<PullRequest>>> = flow {
//        emit(Response.loading())
//
//        val pullRequests =
//            pullRequestMapper.toDomain(githubApi.getClosedPullRequests("closed", 1, 20))
//
//        emit(Response.successOrEmpty(pullRequests))
//
//    }.catch { throwable ->
//        emit(Response.error(throwable))
//    }.flowOn(dispatchersProvider.io())

    override fun getPullRequests(state: String): LiveData<PagingData<PullRequest>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { GithubPagingSource(githubApi, state) }
        ).liveData
    }
}

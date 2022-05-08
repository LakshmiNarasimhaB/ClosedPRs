package com.sample.pulls.data.remote

import androidx.paging.PagingSource
import com.sample.pulls.data.mappers.PullRequestMapper
import com.sample.pulls.domain.model.PullRequest
import retrofit2.HttpException
import java.io.IOException

/**
 * Starting page index for making the first call.
 */
private const val STARTING_PAGE_INDEX = 1

/**
 * Paging source to manage fetching pull requests from Github Api.
 * Includes handling of pagination.
 */
class GithubPagingSource(
    private val githubApi: GithubApi,
    private val state: String,
    private val pullRequestMapper: PullRequestMapper,
) : PagingSource<Int, PullRequest>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val pullRequests = githubApi.getClosedPullRequests(state, position, params.loadSize)
                .map {
                    pullRequestMapper.toPullRequestDomain(it)
                }

            LoadResult.Page(
                data = pullRequests,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (pullRequests.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

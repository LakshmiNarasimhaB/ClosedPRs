package com.sample.pulls.data.mappers

import com.sample.pulls.data.TestData
import org.junit.Assert.*

import org.junit.Test

class PullRequestMapperTest {

    val pullRequestMapper = PullRequestMapper()

    @Test
    fun `test pull request domain model built correctly for a given data model`() {
        val pullRequestData = TestData.PULL_REQUEST_CLOSED
        val mappedPullRequest = pullRequestMapper.toPullRequestDomain(pullRequestData)

        assertEquals(pullRequestData.id, mappedPullRequest.id)
        assertEquals(pullRequestData.number, mappedPullRequest.number)
        assertEquals(pullRequestData.title, mappedPullRequest.title)
        assertEquals(pullRequestData.user.login, mappedPullRequest.user.login)
        assertEquals(pullRequestData.createdAt, mappedPullRequest.createdAt)
        assertEquals(pullRequestData.mergeCommitSha, mappedPullRequest.mergeCommitSha)
        assertEquals(pullRequestData.mergedAt, mappedPullRequest.mergedAt)
        assertEquals(pullRequestData.base.ref, mappedPullRequest.base.ref)
        assertEquals(pullRequestData.base.sha, mappedPullRequest.base.sha)
        assertEquals(pullRequestData.head.ref, mappedPullRequest.head.ref)
        assertEquals(pullRequestData.head.sha, mappedPullRequest.head.sha)
        assertEquals(pullRequestData.state, mappedPullRequest.state)
    }

    @Test
    fun `test pull request domain avatar url built correctly for a null avatar url data model`() {
        val pullRequestData = TestData.PULL_REQUEST_CLOSED
        val mappedPullRequest = pullRequestMapper.toPullRequestDomain(pullRequestData)

        assertEquals(mappedPullRequest.user.avatarUrl, "")
    }

    @Test
    fun `test pull request domain avatar url built correctly for a non null avatar url data model`() {
        val pullRequestData = TestData.PULL_REQUEST_CLOSED
        val mappedPullRequest = pullRequestMapper.toPullRequestDomain(pullRequestData)

        assertEquals(mappedPullRequest.user.avatarUrl, mappedPullRequest.user.avatarUrl)
    }
}

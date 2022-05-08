package com.sample.pulls.data.mappers

import com.sample.pulls.data.model.BranchData
import com.sample.pulls.data.model.PullRequestData
import com.sample.pulls.data.model.UserData
import com.sample.pulls.domain.model.Branch
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.domain.model.User

/**
 * Data to Domain Mapper for PullRequestResponse.
 */
class PullRequestMapper {
    fun toPullRequestDomain(pullRequestData: PullRequestData): PullRequest {
        return PullRequest(
            pullRequestData.id,
            pullRequestData.number,
            pullRequestData.title,
            pullRequestData.body,
            pullRequestData.createdAt,
            pullRequestData.mergedAt,
            pullRequestData.closedAt,
            pullRequestData.mergeCommitSha,
            toHead(pullRequestData.head),
            toHead(pullRequestData.base),
            toUser(pullRequestData.user),
            pullRequestData.state
        )
    }

    private fun toHead(branchData: BranchData) = Branch(branchData.ref, branchData.sha)

    private fun toUser(userData: UserData) =
        User(userData.login, if (userData.avatarUrl.isNullOrBlank()) "" else userData.avatarUrl)
}

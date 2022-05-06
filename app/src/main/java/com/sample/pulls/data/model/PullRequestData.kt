package com.sample.pulls.data.model

import com.sample.pulls.domain.model.Branch
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.domain.model.User
import java.util.*

/**
 * Remote data response for the get closed pull requests call.
 */
data class PullRequestData(
    val id: String,
    val number: String,
    val title: String,
    val body: String,
    val createdAt: Date,
    val mergedAt: Date,
    val closedAt: Date,
    val mergeCommitSha: String,
    val head: BranchData,
    val base: BranchData,
    val user: UserData
) {
    fun toDomain() = PullRequest(
        id,
        number,
        title,
        body,
        createdAt,
        mergedAt,
        closedAt,
        mergeCommitSha,
        head.toDomain(),
        base.toDomain(),
        user.toDomain()
    )
}

/**
 * Branch details to identify Reference and Head branches.
 */
data class BranchData(
    val ref: String,
    val sha: String
) {
    fun toDomain() = Branch(ref, sha)
}

/**
 * User details.
 */
data class UserData(
    val login: String,
    val avatarUrl: String
) {
    fun toDomain() = User(login, avatarUrl)
}

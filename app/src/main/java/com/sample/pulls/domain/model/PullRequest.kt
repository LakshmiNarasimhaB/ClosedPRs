package com.sample.pulls.domain.model

import java.util.*

/**
 * Domain model for
 */
class PullRequest(
    val id: String,
    val number: String,
    val title: String,
    val body: String,
    val createdAt: Date,
    val mergedAt: Date,
    val closedAt: Date,
    val mergeCommitSha: String,
    val head: Branch,
    val base: Branch,
    val user: User
)

/**
 * Branch details to identify Reference and Head branches.
 */
data class Branch(
    val ref: String,
    val sha: String
)

/**
 * User details.
 */
data class User(
    val login: String,
    val avatarUrl: String
)

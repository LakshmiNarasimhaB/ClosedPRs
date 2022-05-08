package com.sample.pulls.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Remote data response for the get closed pull requests call.
 */
data class PullRequestData(
    val id: String,
    val number: String,
    val title: String,
    val body: String,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("merged_at")
    val mergedAt: Date?,
    @SerializedName("closed_at")
    val closedAt: Date?,
    @SerializedName("merge_commit_sha")
    val mergeCommitSha: String?,
    val head: BranchData,
    val base: BranchData,
    val user: UserData,
    val state: String
)

/**
 * Branch details to identify Reference and Head branches.
 */
data class BranchData(
    val ref: String,
    val sha: String
)

/**
 * User details.
 */
data class UserData(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)

package com.sample.pulls.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Domain model for [PullRequestData]
 */
@Parcelize
data class PullRequest(
    val id: String,
    val number: String,
    val title: String,
    val body: String,
    val createdAt: Date,
    val mergedAt: Date?,
    val closedAt: Date?,
    val mergeCommitSha: String?,
    val head: Branch,
    val base: Branch,
    val user: User,
    val state: String
) : Parcelable

/**
 * Branch details to identify Reference and Head branches.
 */
@Parcelize
data class Branch(
    val ref: String,
    val sha: String
) : Parcelable

/**
 * User details.
 */
@Parcelize
data class User(
    val login: String,
    val avatarUrl: String
) : Parcelable

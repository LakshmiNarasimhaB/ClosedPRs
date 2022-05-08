package com.sample.pulls.data

import com.sample.pulls.data.model.BranchData
import com.sample.pulls.data.model.PullRequestData
import com.sample.pulls.data.model.UserData
import java.util.*

object TestData {
    val USER_WITH_AVATAR = UserData("Lakshmi", "dummyUrl")

    val USER_WITHOUT_AVATAR = UserData("Lakshmi", null)

    val BRANCH_HEAD = BranchData("NAVI_100_first_pull_request_branch", "dummySha")

    val BRANCH_REF = BranchData("master", "masterDummySha")

    val PULL_REQUEST_CLOSED = PullRequestData(
        id = "1",
        number = "1",
        title = "NAVI_101_first_pull_request_title_closed",
        body = "first pull request closed body",
        createdAt = Date(),
        mergedAt = null,
        closedAt = Date(),
        mergeCommitSha = "dummyMergeCommitSha",
        head = BRANCH_HEAD,
        base = BRANCH_REF,
        user = USER_WITHOUT_AVATAR,
        state = "closed"
    )

    val PULL_REQUEST_OPEN = PullRequestData(
        id = "2",
        number = "2",
        title = "NAVI_102_first_pull_request_title_open",
        body = "first pull request open body",
        createdAt = Date(),
        mergedAt = null,
        closedAt = null,
        mergeCommitSha = "dummyMergeCommitSha",
        head = BRANCH_HEAD,
        base = BRANCH_REF,
        user = USER_WITHOUT_AVATAR,
        state = "open"
    )

    val PULL_REQUEST_MERGED = PullRequestData(
        id = "1",
        number = "1",
        title = "NAVI_100_first_pull_request_title",
        body = "first pull request body",
        createdAt = Date(),
        mergedAt = Date(),
        closedAt = Date(),
        mergeCommitSha = "dummyMergeCommitSha",
        head = BRANCH_HEAD,
        base = BRANCH_REF,
        user = USER_WITH_AVATAR,
        state = "closed"
    )
}


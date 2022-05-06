package com.sample.pulls.data.mappers

import com.sample.pulls.data.model.PullRequestData
import com.sample.pulls.domain.model.PullRequest

/**
 * Data to Domain Mapper for PullRequestResponse.
 */
class PullRequestMapper {
    fun toDomain(pullRequestData: List<PullRequestData>): List<PullRequest> {
        return pullRequestData.map { it.toDomain() }
    }
}

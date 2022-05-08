package com.sample.pulls.presentation.pullslist.model

/**
 * Enum to showcase the available states to fetch the pull requests.
 */
enum class PullState(val state: String) {
    /**
     * Fetch all closed Pulls, includes both merged and closed pulls.
     */
    CLOSED("closed"),
    /**
     * Fetch all open Pulls.
     */
    OPEN("open"),
    /**
     * Fetch all Pulls, includes both merged, closed and open pulls.
     */
    ALL("all");

    companion object {
        fun fromPosition(position: Int): String {
            return values().first { it.ordinal == position }.state
        }
    }
}

package com.sample.pulls.presentation.model

enum class PullState(val state: String) {
    CLOSED("closed"), OPEN("open"), ALL("all");

    companion object {
        fun fromPosition(position: Int): String {
            return values().first { it.ordinal == position }.state
        }
    }
}

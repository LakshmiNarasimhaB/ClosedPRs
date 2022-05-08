package com.sample.pulls.presentation.pullslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.pulls.R
import com.sample.pulls.databinding.ItemPullRequestBinding
import com.sample.pulls.domain.model.PullRequest
import com.sample.pulls.presentation.model.PullState
import com.sample.pulls.utils.getDateWithDay

class PullRequestListAdapter(
    private val onItemClicked: (PullRequest) -> Unit
) : PagingDataAdapter<PullRequest, PullRequestListAdapter.PullRequestViewHolder>(comparator) {

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val binding = ItemPullRequestBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PullRequestViewHolder(binding)
    }

    inner class PullRequestViewHolder(
        private val binding: ItemPullRequestBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        onItemClicked(item)
                    }
                }
            }
        }

        fun bind(pullRequest: PullRequest) {
            binding.apply {
                textPullTitle.text = pullRequest.title
                textClosedAtValue.text = getDateWithDay(pullRequest.closedAt)
                textOpenedAtValue.text = getDateWithDay(pullRequest.createdAt)
                textAuthorValue.text = pullRequest.user.login
                textBaseBranchValue.text = pullRequest.base.ref
                textHeadBranchValue.text = pullRequest.head.ref
                Glide.with(itemView)
                    .load(pullRequest.user.avatarUrl)
                    .circleCrop()
                    .error(R.drawable.ic_splash)
                    .into(imageAuthorAvatar)
                var imageDrawableId = R.drawable.ic_git_merged
                var backgroundDrawableId = R.drawable.background_round_grey
                var labelText = ""
                when {
                    isPullRequestOpen(pullRequest) -> {
                        backgroundDrawableId = R.drawable.background_round_green
                        labelText = "Open"
                        imageDrawableId = R.drawable.ic_git_open
                    }
                    isPullRequestMerged(pullRequest) -> {
                        backgroundDrawableId = R.drawable.background_round_purple
                        labelText = "Merged"
                        imageDrawableId = R.drawable.ic_git_merged
                    }
                    isPullRequestClosed(pullRequest) -> {
                        backgroundDrawableId = R.drawable.background_round_red
                        labelText = "Closed"
                        imageDrawableId = R.drawable.ic_git_closed
                    }
                    else -> {
                       // Do nothing
                    }
                }
                imageStatus.setImageResource(imageDrawableId)
                textClosedAt.text = labelText
                textClosedAt.background = ContextCompat.getDrawable(
                    binding.divider.context, backgroundDrawableId
                )
                textMoreInfo.setOnClickListener {
                    if (extraInfoGroup.isVisible) {
                        extraInfoGroup.visibility = View.GONE
                        textMoreInfo.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, R.drawable.ic_arrow_drop_down_24, 0
                        )
                    } else {
                        extraInfoGroup.visibility = View.VISIBLE
                        textMoreInfo.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, R.drawable.ic_arrow_drop_up_24, 0
                        )
                    }
                }
            }
        }
    }

    private fun isPullRequestClosed(pullRequest: PullRequest): Boolean {
        return pullRequest.state == PullState.CLOSED.state && pullRequest.mergedAt == null
    }

    private fun isPullRequestOpen(pullRequest: PullRequest): Boolean {
        return pullRequest.state == PullState.OPEN.state
    }

    private fun isPullRequestMerged(pullRequest: PullRequest): Boolean {
        return pullRequest.state == PullState.CLOSED.state && pullRequest.mergedAt != null
    }
}

interface OnItemClickListener {
    fun onItemClick(pullRequest: PullRequest)
}

private val comparator = object : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest) =
        oldItem == newItem
}

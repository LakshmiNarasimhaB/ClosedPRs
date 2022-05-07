package com.sample.pulls.presentation.pullslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.pulls.R
import com.sample.pulls.databinding.ItemPullRequestBinding
import com.sample.pulls.domain.model.PullRequest

class PullRequestListAdapter(
    private val onItemClicked: (PullRequest) -> Unit
) : PagingDataAdapter<PullRequest, PullRequestListAdapter.PullRequestViewHolder>(diffUtil) {

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
                textClosedAt.text = pullRequest.closedAt.toString()
                textOpenedAt.text = pullRequest.createdAt.toString()
                textAuthorDetails.text = itemView.context.getString(
                    R.string.author_merged_from_branch_to_branch,
                    pullRequest.user.login,
                    pullRequest.base.ref, pullRequest.head.ref
                )
                Glide.with(itemView)
                    .load(pullRequest.user.avatarUrl)
                    .circleCrop()
                    .error(R.drawable.ic_splash)
                    .into(imageAuthorAvatar)
            }
        }
    }

    private fun sortItems(){

    }
}

interface OnItemClickListener {
    fun onItemClick(pullRequest: PullRequest)
}

private val diffUtil = object : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest) =
        oldItem == newItem
}

package com.sample.pulls.presentation.pullslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.sample.pulls.R
import com.sample.pulls.databinding.FragmentPullRequestListBinding
import com.sample.pulls.domain.model.PullRequest

class PullRequestListFragment : Fragment(R.layout.fragment_pull_request_list) {

    private val viewModel: PullRequestsListViewModel by viewModels { PullRequestListViewModelFactory }
    private var _binding: FragmentPullRequestListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PullRequestListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPullRequestListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupPullStateSpinner()
        observePullRequestsFetch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupPullStateSpinner() {
        binding.spinnerPullState.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.pulls_state_array,
            android.R.layout.simple_spinner_item
        ).also { spinnerAdapter ->
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerPullState.onItemSelectedListener = stateSpinnerItemSelectedListener
        binding.spinnerPullState.setSelection(0)
    }

    private fun setupUi() {
        adapter = PullRequestListAdapter(this::onItemClicked)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PullRequestLoadStateAdapter { adapter.retry() },
                footer = PullRequestLoadStateAdapter { adapter.retry() }
            )
            retryGroup.setOnClickListener { adapter.retry() }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryGroup.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                }
            }
        }
    }

    private fun observePullRequestsFetch() {
//        viewModel.getPullRequestsForState(CLOSED)
        viewModel.pullRequests.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun onItemClicked(pullRequest: PullRequest) {
        // Navigate to details.
    }

    private val stateSpinnerItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            viewModel.getPullRequestsForState(pos)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Do nothing.
        }
    }
}

package com.sample.pulls.presentation.pullslist.view

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
import com.sample.pulls.presentation.pullslist.adapter.PullRequestListAdapter
import com.sample.pulls.presentation.pullslist.adapter.PullRequestLoadStateAdapter
import com.sample.pulls.presentation.pullslist.viewmodel.PullRequestListViewModelFactory
import com.sample.pulls.presentation.pullslist.viewmodel.PullRequestsListViewModel

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
        setupRecyclerViewAdapter()
        setupPullStateSpinner()
        observePullRequestsFetch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        binding.apply {
            buttonRetry.setOnClickListener { adapter.retry() }
            retryGroup.setOnClickListener { adapter.retry() }
        }
    }

    private fun setupRecyclerViewAdapter(){
        adapter = PullRequestListAdapter()
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PullRequestLoadStateAdapter { adapter.retry() },
                footer = PullRequestLoadStateAdapter { adapter.retry() }
            )
        }
        addLoadStateListenerToRecyclerView()
    }

    /**
     * Load state listener to adapter. Show/Hide progressbar and retry buttons based on state.
     */
    private fun addLoadStateListenerToRecyclerView() {
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                spinnerPullState.isEnabled = loadState.source.refresh is LoadState.NotLoading
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

    /**
     * Populate spinner to select what state of pull request to load.
     */
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

    /**
     * Observe the changes in the pullRequests Mutable Live Data and update the recycler view.
     */
    private fun observePullRequestsFetch() {
        viewModel.pullRequests.observe(viewLifecycleOwner) {
            binding.recyclerView.scrollToPosition(0)
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    /**
     * On Item selected listener to update required state to View Model.
     */
    private val stateSpinnerItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            viewModel.getPullRequestsForState(pos)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Do nothing.
        }
    }
}

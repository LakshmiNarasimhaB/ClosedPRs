package com.sample.pulls.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sample.pulls.databinding.ActivityMainBinding

class PullRequestsListActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        val viewModel = ViewModelProvider(this).get(PullRequestsListViewModel::class.java)
        viewModel.getPullRequests()
    }
}

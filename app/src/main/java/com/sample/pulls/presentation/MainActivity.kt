package com.sample.pulls.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.sample.pulls.R
import com.sample.pulls.SampleRepositoryApplication
import com.sample.pulls.di.AppContainer
import com.sample.pulls.di.PullRequestListContainer

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Instantiate pull request list container when the holding activity is created.
         */
        appContainer = (application as SampleRepositoryApplication).appContainer
        appContainer.pullRequestListContainer = PullRequestListContainer(appContainer.repository!!)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main)
                as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    /**
     * Destroy pull request list container when the holding activity is destroyed, helping the memory management.
     */
    override fun onDestroy() {
        super.onDestroy()
        appContainer.pullRequestListContainer = null
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

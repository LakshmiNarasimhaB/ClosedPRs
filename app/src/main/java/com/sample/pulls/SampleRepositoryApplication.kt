package com.sample.pulls

import android.app.Application
import com.sample.pulls.di.AppContainer

/**
 * Application class to instantiate app container on launching the app.
 */
class SampleRepositoryApplication : Application() {

    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}

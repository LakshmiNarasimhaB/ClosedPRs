package com.sample.pulls

import android.app.Application

/**
 * Application class to instantiate app container on launching the app.
 */
class SampleRepositoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appContainer = AppContainer()
    }
}

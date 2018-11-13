package com.alex.githubusers.application

import android.app.Application
import com.alex.githubusers.di.components.ApplicationComponent
import com.alex.githubusers.di.components.DaggerApplicationComponent
import com.alex.githubusers.di.modules.ApplicationModule

class GitHubUsersApp : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
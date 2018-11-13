package com.alex.githubusers.di.components

import android.content.Context
import com.alex.githubusers.di.modules.ApplicationModule
import com.alex.githubusers.di.modules.NetworkModule
import com.alex.githubusers.network.ApiRequestService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun context(): Context

    fun apiRequestService(): ApiRequestService
}
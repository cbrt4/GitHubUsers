package com.alex.githubusers.di.components

import com.alex.githubusers.util.ScreenScope
import com.alex.githubusers.view.activities.MainActivity
import dagger.Component


@ScreenScope
@Component(dependencies = [ApplicationComponent::class])
interface ScreenComponent {

    fun inject(activity: MainActivity)
}
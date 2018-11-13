package com.alex.githubusers.view.activities

import android.os.Bundle
import com.alex.githubusers.R
import com.alex.githubusers.application.GitHubUsersApp
import com.alex.githubusers.di.components.DaggerScreenComponent
import com.alex.githubusers.model.UserEntity
import com.alex.githubusers.util.Layout
import com.alex.githubusers.util.ScreenScope

@ScreenScope
@Layout(id = R.layout.activity_main)
class MainActivity : AbstractActivity<List<UserEntity>>() {
    override fun inject() {
        DaggerScreenComponent.builder()
            .applicationComponent((application as GitHubUsersApp).applicationComponent)
            .build().inject(this)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun update(element: List<UserEntity>) {

    }

    override fun reportError(error: Throwable) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

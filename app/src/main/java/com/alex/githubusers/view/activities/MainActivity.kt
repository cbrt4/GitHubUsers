package com.alex.githubusers.view.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.alex.githubusers.R
import com.alex.githubusers.application.GitHubUsersApp
import com.alex.githubusers.di.components.DaggerScreenComponent
import com.alex.githubusers.listeners.OnItemClickListener
import com.alex.githubusers.model.UserEntity
import com.alex.githubusers.presenters.MainPresenter
import com.alex.githubusers.util.Layout
import com.alex.githubusers.util.ScreenScope
import com.alex.githubusers.view.adapters.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@ScreenScope
@Layout(id = R.layout.activity_main)
class MainActivity : AbstractActivity<List<UserEntity>>(), OnItemClickListener {

    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var userAdapter: UserAdapter

    private val perPage = 25
    private val itemsToLoad = 15

    private var since = 0
    private var isLoading = false
    private var allUsersLoaded = false
    private var backPressedTimeOut: Long = 0

    private val users = arrayListOf<UserEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()

        mainPresenter.view = this
        mainPresenter.loadUsers(since, perPage)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.destroy()
    }

    override fun onBackPressed() {
        if (backPressedTimeOut + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, R.string.on_back_pressed, Toast.LENGTH_SHORT).show()
            backPressedTimeOut = System.currentTimeMillis()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        userAdapter.users = users
        userAdapter.listener = this
        usersRecyclerView.layoutManager = layoutManager
        usersRecyclerView.adapter = userAdapter

        usersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isLoading && !allUsersLoaded) {
                    val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                    val visibleItemCount = layoutManager.itemCount
                    if (lastVisiblePosition >= visibleItemCount - itemsToLoad && visibleItemCount > 0) {
                        mainPresenter.loadUsers(since, perPage)
                    }
                }
            }
        })
    }

    override fun inject() {
        DaggerScreenComponent.builder()
            .applicationComponent((application as GitHubUsersApp).applicationComponent)
            .build().inject(this)
    }

    override fun showLoading() {
        isLoading = true
        loadingProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        isLoading = false
        loadingProgressBar.visibility = View.GONE
    }

    override fun update(element: List<UserEntity>) {
        allUsersLoaded = element.isEmpty()
        if (!allUsersLoaded) {
            since = element[element.size - 1].id
            users.addAll(element)
            userAdapter.notifyItemRangeChanged(
                userAdapter.itemCount,
                perPage
            )
        }
    }

    override fun reportError(error: Throwable) {
        showToast(error.localizedMessage)
    }

    override fun onItemClick(position: Int) {
        showToast(users[position].login)
    }
}

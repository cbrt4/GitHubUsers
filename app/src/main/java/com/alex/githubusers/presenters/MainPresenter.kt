package com.alex.githubusers.presenters

import android.util.Log
import com.alex.githubusers.model.UserEntity
import com.alex.githubusers.network.ApiRequestService
import com.alex.githubusers.util.ScreenScope
import com.alex.githubusers.view.AbstractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ScreenScope
class MainPresenter @Inject constructor(private val apiRequestService: ApiRequestService) :
    AbstractPresenter<AbstractView<List<UserEntity>>>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadUsers(since: Int, perPage: Int) {
        view?.showLoading()
        apiRequestService.loadUsers(since, perPage)

        compositeDisposable.add(apiRequestService.loadUsers(since, perPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.hideLoading()
                view?.update(it)
            }, {
                view?.hideLoading()
                view?.reportError(it)
                Log.e("Load", "Error", it)
            }))
    }

    override fun cancel() {
        view?.hideLoading()
        compositeDisposable.clear()
    }

    override fun destroy() {
        view = null
        compositeDisposable.dispose()
    }
}
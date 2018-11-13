package com.alex.githubusers.presenters

abstract class AbstractPresenter<V> {

    var view: V? = null

    abstract fun cancel()

    abstract fun destroy()
}
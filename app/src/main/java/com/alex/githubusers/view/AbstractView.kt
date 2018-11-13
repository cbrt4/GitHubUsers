package com.alex.githubusers.view

interface AbstractView<E> {

    fun showLoading()

    fun hideLoading()

    fun update(element: E)

    fun reportError(error: Throwable)
}
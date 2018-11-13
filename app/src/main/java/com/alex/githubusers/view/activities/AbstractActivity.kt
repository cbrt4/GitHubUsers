package com.alex.githubusers.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.alex.githubusers.util.Layout
import com.alex.githubusers.view.AbstractView
import kotlinx.android.synthetic.*

abstract class AbstractActivity<E> : AppCompatActivity(), AbstractView<E> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        javaClass.getAnnotation(Layout::class.java)?.let {
            setContentView(it.id)
        }

        inject()
    }

    abstract fun inject()

    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
package com.alex.githubusers.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = context
}
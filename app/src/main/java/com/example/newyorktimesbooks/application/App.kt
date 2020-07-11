package com.example.newyorktimesbooks.application

import android.app.Application
import android.content.Context
import com.example.newyorktimesbooks.di.dataModule
import com.example.newyorktimesbooks.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule, dataModule)
        }

        setContext(applicationContext)

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        private lateinit var CONTEXT: Context

        private fun setContext(context: Context) {
            synchronized(App::class.java) {
                if (!::CONTEXT.isInitialized) {
                    CONTEXT = context
                }
            }
        }

        fun getContext() = CONTEXT
    }
}
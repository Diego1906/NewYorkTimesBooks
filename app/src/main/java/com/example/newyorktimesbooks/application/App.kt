package com.example.newyorktimesbooks.application

import android.app.Application
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

        Timber.plant(Timber.DebugTree())
    }
}
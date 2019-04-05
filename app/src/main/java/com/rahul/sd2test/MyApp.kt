package com.rahul.sd2test

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.rahul.sd2test.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    companion object {
        lateinit var INSTANCE: Application
        var BEARER_TOKEN = ""
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        setupKoin()

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}
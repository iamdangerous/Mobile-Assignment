package com.rahul.sd2test

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.rahul.sd2test.extras.ApiEndpoints
import com.rahul.sd2test.presenter.app.ApplicationPresenter
import com.rahul.sd2test.webService.ApiService
import okhttp3.OkHttpClient

class MyApp:Application() {
    companion object {
        lateinit var INSTANCE: Application
        var BEARER_TOKEN = ""
        lateinit var apiInterface: ApiService
    }

    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        val presenter = ApplicationPresenter()
        okHttpClient = presenter.createOkHttpClient()
        apiInterface = presenter.createWebService<ApiService>(okHttpClient, ApiEndpoints.BASE_URL)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
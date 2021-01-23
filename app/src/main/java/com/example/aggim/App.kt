package com.example.aggim

import android.app.Application
import com.example.aggim.api.AggimApi.Companion.instance

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}
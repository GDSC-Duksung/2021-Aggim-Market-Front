package com.example.aggim

import android.app.Application
import com.example.aggim.api.AggimApi.Companion.instance

/*
    Created by Seohyun Kim at 2021/01/22
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}
package com.example.aggim.api

/*
    created by Jin Lee 2021/02/01
*/

import android.content.Intent
import com.example.aggim.App
import com.example.aggim.common.Prefs
import com.example.aggim.signin.SigninActivity
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.intentFor

class ApiTokenInterceptor: Interceptor, AnkoLogger{
    override fun intercept(chain: Interceptor.Chain): Response {
        debug("API 요청")
        val original = chain.request()
        val request = original.newBuilder().apply {
            Prefs.token?.let{ header("Authorization", it) }
            method(original.method, original.body)
            //method(original.method(), original.body())
        }.build()

        return chain.proceed(request)
    }
}
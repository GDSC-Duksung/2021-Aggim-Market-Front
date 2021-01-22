package com.example.aggim.intro

/*
    Created by Seohyun Kim on 2021/01/20
 */

import android.app.Activity
import org.jetbrains.anko.setContentView
import android.os.Bundle
import android.util.Log
import com.example.aggim.api.AggimApi
import com.example.aggim.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.startActivity
import java.lang.Exception

class IntroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = IntroActivityUI()
        ui.setContentView(this)

        GlobalScope.launch {
            delay(1000)
            startActivity<SignupActivity>()
            finish()
        }

//        runBlocking {
//            try {
//                val response = AggimApi.instance.hello()
//                Log.d("IntroActivity", response.data)
//            } catch (e: Exception) {
//                Log.e("IntroActivity", "Hello API 호출 오류", e)
//            }
//        }
    }
}
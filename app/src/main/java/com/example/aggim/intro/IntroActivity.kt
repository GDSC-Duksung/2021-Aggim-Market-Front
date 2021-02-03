package com.example.aggim.intro

/*
    Created by Seohyun Kim on 2021/01/20
 */

import android.app.Activity
import org.jetbrains.anko.setContentView
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aggim.R
import com.example.aggim.api.AggimApi
import com.example.aggim.donation.DonatesListActivity
import com.example.aggim.product.ProductMainActivity
import com.example.aggim.product.detail.ProductDetailActivity
import com.example.aggim.product.registration.ProductRegistrationActivity
import com.example.aggim.main.MainPageActivity
import com.example.aggim.mypage.main.MyPageMain
import com.example.aggim.signin.SigninActivity
import com.example.aggim.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.startActivity
import java.lang.Exception

// 메인 화면 입니다.
class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_page)

        GlobalScope.launch {
            delay(1500)
            startActivity<ProductMainActivity>() // 화면을 테스트 하려면 여기에 액티비티 이름 입력
            finish()
        }
    }
}

//class IntroActivity : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val ui = IntroActivityUI()
//        ui.setContentView(this)
//
//        GlobalScope.launch {
//            delay(1000)
//            startActivity<MainPageActivity>() // 화면을 테스트 하려면 여기에 액티비티 이름 입력
//            finish()
//        }

//        runBlocking {
//            try {
//                val response = AggimApi.instance.hello()
//                Log.d("IntroActivity", response.data)
//            } catch (e: Exception) {
//                Log.e("IntroActivity", "Hello API 호출 오류", e)
//            }
//        }
//    }
//}
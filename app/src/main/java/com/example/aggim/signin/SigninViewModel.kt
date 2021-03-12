package com.example.aggim.signin

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.aggim.api.AggimApi
import com.example.aggim.api.request.SigninRequest
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.SigninResponse
import com.example.aggim.common.Prefs
import com.example.aggim.mypage.updateInfo.UpdateInfoActivity
import com.example.aggim.product.ProductMainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

/*
    Created by Seohyun Kim at 2021/01/22
 */

class SigninViewModel(app: Application) : BaseViewModel(app) {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signin() {
        val request = SigninRequest(email.value, password.value)
        if (isNotValidSignin(request))
            return

        try {
            val response  = requestSignin(request)
            onSigninReponse(response)
        } catch (e: Exception) {
            error("Signin error", e)
            toast("알 수 없는 오류가 발생했습니다.")
        }
    }

    private fun isNotValidSignin(request: SigninRequest)=
        when {
            request.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            request.isNotValidPassword() -> {
                toast("비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }

    private suspend fun requestSignin(request: SigninRequest) =
        withContext(Dispatchers.IO) {
            AggimApi.instance.signin(request)
        }

    private fun onSigninReponse(response: ApiResponse<SigninResponse>) {
        if(response.success && response.data != null) {
            Prefs.token = response.data.token
            Prefs.refreshToken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId
            Prefs.email = response.data.email

            toast("로그인되었습니다.")
            startActivityAndFinish<ProductMainActivity>()
        } else {
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}
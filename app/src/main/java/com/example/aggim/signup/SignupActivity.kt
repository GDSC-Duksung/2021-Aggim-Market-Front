package com.example.aggim.signup

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

/*
    Created by Seohyun Kim at 2021/01/21
 */

class SignupActivity : BaseActivity<SignupViewModel>() {
    override val viewModelType = SignupViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SignupActivityUI(getViewModel())
            .setContentView(this)
    }
}
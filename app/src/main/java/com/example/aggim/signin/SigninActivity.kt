package com.example.aggim.signin

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

/*
    Created by Seohyun Kim at 2021/01/22
 */

class SigninActivity : BaseActivity<SigninViewModel>() {
    override val viewModelType = SigninViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SigninActivityUI(getViewModel())
                .setContentView(this)
    }
}

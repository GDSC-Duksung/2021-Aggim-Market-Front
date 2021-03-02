package com.example.aggim.signup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

/*
    Created by Seohyun Kim at 2021/01/21
    Update by Jin Lee at 2021/02/04
 */

class SignupActivity : BaseActivity<SignupViewModel>() {
    override val viewModelType = SignupViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SignupActivityUI(getViewModel())
            .setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=""
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F1F2F9")))
        supportActionBar?.setElevation(0f)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let{
            when(item.itemId){
                android.R.id.home -> onBackPressed()
                else ->{}
            }
        }
        return true
    }
}
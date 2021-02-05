package com.example.aggim.signin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.aggim.api.response.SigninResponse
import com.example.aggim.product.ProductMainActivity
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

/*
    Created by Seohyun Kim at 2021/01/22
    Updated by Jin Lee at 2021/02/04
 */

class SigninActivity : BaseActivity<SigninViewModel>() {
    override val viewModelType = SigninViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SigninActivityUI(getViewModel())
                .setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="홈으로"
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

    override fun onBackPressed() {
        startActivity(Intent(this, ProductMainActivity::class.java))
        finish()
    }
}


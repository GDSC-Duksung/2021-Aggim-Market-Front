package com.example.aggim.product.registration

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

/*
    Created by Jin Lee on 2021/01/31
 */

class ProductRegistrationActivity:
    BaseActivity<ProductRegistrationViewModel>(){

    override val viewModelType = ProductRegistrationViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductRegistrationUI(getViewModel())
            .setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Product Registration"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F1F2F9")))
        supportActionBar?.setElevation(0f)
    }
    //뒤로가기 버튼
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


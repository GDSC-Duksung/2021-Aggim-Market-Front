package com.example.aggim.product.search

/*
    Created by Jin Lee on 2021/01/31
 */

import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductSearchActivity : BaseActivity<ProductSearchViewModel>(){

    override val viewModelType = ProductSearchViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyword = intent.getStringArrayExtra(KEYWORD)//키값 keyword
        val viewModel = getViewModel().apply {
            this.keyword = keyword.toString()
        }

        ProductSearchUI(viewModel).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = keyword.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    companion object{
        const val KEYWORD = "keyword"
    }
}
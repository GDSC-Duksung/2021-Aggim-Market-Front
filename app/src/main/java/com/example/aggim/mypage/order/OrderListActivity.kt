package com.example.aggim.mypage.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aggim.R

/*
Updated by Jin Lee on 2021/02/04
 */

import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_my_page_main.toolbar

class OrderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "주문 목록"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }
}
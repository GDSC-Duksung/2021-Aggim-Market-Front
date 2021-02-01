package com.example.aggim.mypage.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.aggim.R
import com.example.aggim.mypage.cart.CartActivity
import com.example.aggim.mypage.orderList.OrderListActivity
import com.example.aggim.mypage.stamp.StampActivity
import com.example.aggim.mypage.updateInfo.UpdateInfoActivity
import kotlinx.android.synthetic.main.activity_my_page_main.*
import kotlinx.android.synthetic.main.activity_my_page_main.toolbar
import kotlinx.android.synthetic.main.activity_stamp.*

class MyPageMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_main)

        toolbar.setTitle("My Page")
        setSupportActionBar(toolbar)

        val pages = ArrayList<String>()

        pages.add("회원 정보 수정")
        pages.add("스탬프")
        pages.add("장바구니")
        pages.add("주문목록")

        val pages_Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pages)
        listPages.adapter = pages_Adapter

        listPages.choiceMode = ListView.CHOICE_MODE_SINGLE

        listPages.setOnItemClickListener { parent, view, position, id ->
            //Toast.makeText(this, cities.get(position) + "를 클릭하셨습니다.", Toast.LENGTH_SHORT).show() // 선택한 데이터 출력
            if(position == 0) {
                val nextIntent = Intent(this, UpdateInfoActivity::class.java)
                startActivity(nextIntent)
            } else if (position == 1) {
                val nextIntent = Intent(this, StampActivity::class.java)
                startActivity(nextIntent)
            } else if(position == 2) {
                val nextIntent = Intent(this, CartActivity::class.java)
                startActivity(nextIntent)
            } else {
                val nextIntent = Intent(this, OrderListActivity::class.java)
                startActivity(nextIntent)
            }
        }
    }
}
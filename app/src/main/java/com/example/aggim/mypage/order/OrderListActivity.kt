<<<<<<< HEAD:app/src/main/java/com/example/aggim/mypage/order/OrderListActivity.kt
package com.example.aggim.mypage.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aggim.R
=======
package com.example.aggim.mypage.orderList
/*
Updated by Jin Lee on 2021/02/04
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.aggim.R
import kotlinx.android.synthetic.main.activity_my_page_main.*
import kotlinx.android.synthetic.main.activity_my_page_main.toolbar
import kotlinx.android.synthetic.main.activity_stamp.*
>>>>>>> 9444e0437c94373dd15123447441b976ebff390c:app/src/main/java/com/example/aggim/mypage/orderList/OrderListActivity.kt

class OrderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD:app/src/main/java/com/example/aggim/mypage/order/OrderListActivity.kt
        setContentView(R.layout.order_list_activity_main)
=======
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
>>>>>>> 9444e0437c94373dd15123447441b976ebff390c:app/src/main/java/com/example/aggim/mypage/orderList/OrderListActivity.kt
    }
}
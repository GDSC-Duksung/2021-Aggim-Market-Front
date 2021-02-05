package com.example.aggim.mypage.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aggim.R
import com.example.aggim.common.Prefs
import com.example.aggim.mypage.buy.BuyProduct
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val adapter = RecyclerAdapterItems(Prefs.cList)
        rv_view.adapter = adapter
        rv_view.layoutManager = LinearLayoutManager(this)
        rv_view.setHasFixedSize(true)
        //xml_cit_button.setOnClickListener{
        //   Prefs.cList.removeAt(pos)
        //}
        var sum = 0
        if(Prefs.cList.size > 0) {
            for(i in 0..Prefs.cList.size-1) {
                sum += Prefs.cList.get(i).price
            }
        }
        if(sum > 0)
            sumMoney.text = sum.toString()+"원"
        buyItem.setOnClickListener{
            val nextIntent = Intent(this, BuyProduct::class.java)
            startActivity(nextIntent)
        }
    }
}
package com.example.aggim.mypage.buy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.aggim.R
import com.example.aggim.api.AggimApi
import com.example.aggim.api.request.OrderRegistrationRequest
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.common.Prefs
import com.example.aggim.product.ProductMainActivity
import retrofit2.Response

class BuyProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_product)

        val pview : LinearLayout = findViewById(R.id.bpview)
        val intent: Intent = intent
        val textSum : TextView = findViewById(R.id.tv_will_buy)
        val textMinBuy : TextView = findViewById(R.id.tv_min_ship)
        val textRealBuy : TextView = findViewById(R.id.tv_real_buy)
        val cb_donate: CheckBox = findViewById(R.id.cb_will_donate)
        val button_buy: Button = findViewById(R.id.btn_real_buy)
        var realsum: Int = intent.getIntExtra("sum", 0)
        var donateAmount: Int

        textSum.text = "상품 금액: " + intent.getIntExtra("sum", 0).toString()+"₩"
        textMinBuy.text = "무료 배송을 위한 최소 주문 금액: 20000₩"
        if (intent.getIntExtra("sum", 0) >= 20000) {
            textRealBuy.text = "결제하실 금액: "+ realsum.toString() + "₩"
        } else if(intent.getIntExtra("sum", 0) < 20000) {
            textRealBuy.text = "결제하실 금액: "+ (realsum + 3000).toString() + "₩"
            realsum += 3000
        }

        cb_donate.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                val d : EditText = EditText(this)
                d.hint = "기부하실 금액을 입력하세요"
                d.addTextChangedListener(afterTextChanged = { it ->
                    donateAmount = it.toString().toInt()
                    textRealBuy.text = (donateAmount+realsum).toString() + "₩"
                })
                pview.addView(d)
            } else {

            }
        }

        button_buy.setOnClickListener {
            Toast.makeText(this, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this, ProductMainActivity::class.java)
            startActivity(nextIntent)
        }
    }
}

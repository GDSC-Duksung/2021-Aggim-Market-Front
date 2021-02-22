package com.example.aggim.mypage.buy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.example.aggim.R
import com.example.aggim.api.AggimApi
import com.example.aggim.api.request.OrderRegistrationRequest
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.DonateListItemResponse
import com.example.aggim.common.Prefs
import com.example.aggim.donation.DonatesAdapter
import com.example.aggim.donation.DonatesListViewModel
import com.example.aggim.donation.DonatesListViewModelFactory
import com.example.aggim.product.ProductMainActivity
import kotlinx.android.synthetic.main.activity_buy_product.*
import retrofit2.Response

class BuyProductActivity : AppCompatActivity() {
    private val donatesListViewModel by viewModels<DonatesListViewModel> {
        DonatesListViewModelFactory(this)
    }
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
        var spinner_data = mutableListOf<String>()
        var result: String

        var dl = donatesListViewModel.donationsLiveData.value
        val it = dl?.iterator()
        while(it!!.hasNext()) {
            val i = it.next()
            spinner_data.add(i.name)
        }
        Log.d("this is test", "리스트 내용"+spinner_data)
        var spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinner_data)
        donation_spinner.adapter = spinner_adapter

        textSum.text = "상품 금액: " + intent.getIntExtra("sum", 0).toString()+"₩"
        textMinBuy.text = "무료 배송을 위한 최소 주문 금액: 20000₩"
        if (intent.getIntExtra("sum", 0) >= 20000) {
            textRealBuy.text = "결제하실 금액: "+ realsum.toString() + "₩"
        } else if(intent.getIntExtra("sum", 0) < 20000) {
            textRealBuy.text = "결제하실 금액: "+ (realsum + 3000).toString() + "₩"
            realsum += 3000
        }
        donation_spinner.setVisibility(View.INVISIBLE)
        donation_price.setVisibility(View.INVISIBLE)

        cb_donate.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                //val d : EditText = EditText(this)
                //d.hint = "기부하실 금액을 입력하세요"
                //d.addTextChangedListener(afterTextChanged = { it ->
                //    donateAmount = it.toString().toInt()
                //    textRealBuy.text = (donateAmount+realsum).toString() + "₩"
                //})
                //pview.addView(d)
                donation_spinner.setVisibility(View.VISIBLE)
                donation_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        result = spinner_data.get(p2)
                    }

                }
                donation_price.setVisibility(View.VISIBLE)
                donation_price.addTextChangedListener(afterTextChanged = { it ->
                    donateAmount = it.toString().toInt()
                    textRealBuy.text = (donateAmount+realsum).toString() + "₩"
                })
            } else {
                donation_spinner.setVisibility(View.INVISIBLE)
                donation_price.setVisibility(View.INVISIBLE)
            }
        }

        button_buy.setOnClickListener {
            Toast.makeText(this, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this, ProductMainActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
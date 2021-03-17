package com.example.aggim.mypage.buy

import android.animation.ArgbEvaluator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.viewpager.widget.ViewPager
import com.example.aggim.R
import com.example.aggim.donation.DonatesListViewModel
import com.example.aggim.donation.DonatesListViewModelFactory
import com.example.aggim.product.ProductMainActivity
import kotlinx.android.synthetic.main.activity_buy_product.*
<<<<<<< HEAD
import kotlinx.coroutines.runBlocking
import retrofit2.Response
=======
>>>>>>> 061a74c96dff583b9e057687c0c3745a065fb21b

class BuyProductActivity : AppCompatActivity() {
    private val donatesListViewModel by viewModels<DonatesListViewModel> {
        DonatesListViewModelFactory(this)
    }
    private val buyProductViewModel by viewModels<BuyProductViewModel> {
        BuyProductViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipeview_activity_main)

<<<<<<< HEAD
=======
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Cart"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F1F2F9")))
        supportActionBar?.setElevation(0f)

>>>>>>> 061a74c96dff583b9e057687c0c3745a065fb21b
        val pview : LinearLayout = findViewById(R.id.bpview)
        val intent: Intent = intent
        val textSum : TextView = findViewById(R.id.tv_will_buy)
        val textMinBuy : TextView = findViewById(R.id.tv_min_ship)
        val textRealBuy : TextView = findViewById(R.id.tv_real_buy)
        val cb_donate: CheckBox = findViewById(R.id.cb_will_donate)
        val button_buy: Button = findViewById(R.id.btn_real_buy)
        val models: MutableList<Model> = mutableListOf<Model>(
                Model(R.drawable.donation1, "매일 250명에게 희망의 밥을 짓습니다.", "월-금요일, 하루 25만원의 식재료비(쌀, 운영비를 제외한 순수 식재료비)가 필요합니다. 1인당 단돈 1천원으로 영양가득하고 맛있는 아침식사를 250명에게 제공할 수 있습니다. 희망의 밥을 짓는 일에 함께해주세요."),
                Model(R.drawable.donation2, "코로나19 극복 식자재 나누기", "장기화된 코로나19로 인하여 많은 분들이 어려움을 겪고 있는 상황에서 취약계층에 조금이라도 도움을 드리고자 식자재 나누기 사업을 진행하고자 합니. 본 사업을 통하여 취약계층에게 신선한 식재료를 지원하여 식생활 개선에 이바지하며, 경제 상황의 악화로 어려운 상황에 놓인 소상공인에게도 도움을 주고 싶습니다."),
                Model(R.drawable.donation3, "장애아동을 위한 배달음식 지원 사업", "매일 3-40인분의 음식을 하는 일은 누구라도 고된 업무가 될 수 있습니다. 스파인2000에서 맛있는 배달음식을 제공함으로서 시설종사자 분들의 업무를 조금이나마 덜어드려 업무효율성을 높이고, 또한 만족스러운 한 끼 식사를 모두가 함께 할 수 있다면 삶의 만족도도 향상되어 모두가 든든한 세상이 될 것이라 생각됩니다.")
        )
        var adapter: Adapter = Adapter(models, this)
        var realsum: Int = intent.getIntExtra("sum", 0)
        var donateAmount: Int = 0
        var spinner_data = mutableListOf<String>()
        var result: String
<<<<<<< HEAD
        var pos: Int = 0
=======
        var viewPager: ViewPager = findViewById(R.id.viewPager)
        var colors: Array<Int> = arrayOf(
                resources.getColor(R.color.colorPrimary),
                resources.getColor(R.color.colorPrimary),
                resources.getColor(R.color.colorPrimary)
        )
        var argbEvaluator = ArgbEvaluator()

        viewPager.setAdapter(adapter)
        viewPager.setPadding(130, 0, 130, 0)
        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position < adapter.count - 1 && position < colors.size - 1) {
                    viewPager.setBackgroundColor(
                            (argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            ) as Int)
                    )
                } else {
                    viewPager.setBackgroundColor(colors[colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

>>>>>>> 061a74c96dff583b9e057687c0c3745a065fb21b

        var dl = donatesListViewModel.donationsLiveData.value
        val it = dl?.iterator()
        while(it!!.hasNext()) {
            val i = it.next()
            spinner_data.add(i.name)
        }
        Log.d("this is test", "List info"+spinner_data)
        var spinner_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinner_data)
        donation_spinner.adapter = spinner_adapter

        textSum.text = "Sub-total: " + intent.getIntExtra("sum", 0).toString()+"₩"
        textMinBuy.text = "FREE DELIVERY on orders over 20000₩"
        if (intent.getIntExtra("sum", 0) >= 20000) {
            textRealBuy.text = "Total to pay: "+ realsum.toString() + "₩"
        } else if(intent.getIntExtra("sum", 0) < 20000) {
            textRealBuy.text = "Total to pay: "+ (realsum + 3000).toString() + "₩"
            realsum += 3000
        }
        donation_spinner.setVisibility(View.INVISIBLE)
        donation_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result = spinner_data.get(p2)
                pos = p2 + 1
            }

        }
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
<<<<<<< HEAD
            Toast.makeText(this, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            runBlocking {
                if(donateAmount > 0) {
                    buyProductViewModel.registerDonate(donateAmount, pos)
                }
            }
=======
            Toast.makeText(this, "Your order has been placed.", Toast.LENGTH_SHORT).show()
>>>>>>> 061a74c96dff583b9e057687c0c3745a065fb21b
            val nextIntent = Intent(this, ProductMainActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
package com.example.aggim.mypage.buy

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.aggim.R
import java.util.*

class MainActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var adapter: Adapter? = null
    lateinit var models: MutableList<Model>
    var colors: Array<Int>? = null
    var argbEvaluator = ArgbEvaluator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipeview_activity_main)

        models.add(Model(R.drawable.donation1, "매일 250명에게 희망의 밥을 짓습니다.", "월-금요일, 하루 25만원의 식재료비(쌀, 운영비를 제외한 순수 식재료비)가 필요합니다. 1인당 단돈 1천원으로 영양가득하고 맛있는 아침식사를 250명에게 제공할 수 있습니다. 희망의 밥을 짓는 일에 함께해주세요."))
        models.add(Model(R.drawable.donation2, "코로나19 극복 식자재 나누기", "장기화된 코로나19로 인하여 많은 분들이 어려움을 겪고 있는 상황에서 취약계층에 조금이라도 도움을 드리고자 식자재 나누기 사업을 진행하고자 합니. 본 사업을 통하여 취약계층에게 신선한 식재료를 지원하여 식생활 개선에 이바지하며, 경제 상황의 악화로 어려운 상황에 놓인 소상공인에게도 도움을 주고 싶습니다."))
        models.add(Model(R.drawable.donation3, "장애아동을 위한 배달음식 지원 사업", "매일 3-40인분의 음식을 하는 일은 누구라도 고된 업무가 될 수 있습니다. 스파인2000에서 맛있는 배달음식을 제공함으로서 시설종사자 분들의 업무를 조금이나마 덜어드려 업무효율성을 높이고, 또한 만족스러운 한 끼 식사를 모두가 함께 할 수 있다면 삶의 만족도도 향상되어 모두가 든든한 세상이 될 것이라 생각됩니다."))
        adapter = Adapter(models, this)

        viewPager = findViewById(R.id.viewPager)
        viewPager!!.setAdapter(adapter)
        viewPager!!.setPadding(130, 0, 130, 0)
        val colors_temp = arrayOf(
                resources.getColor(R.color.colorPrimary),
                resources.getColor(R.color.colorPrimary),
                resources.getColor(R.color.colorPrimary)
        )
        colors = colors_temp
        viewPager!!.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position < adapter!!.count - 1 && position < colors!!.size - 1) {
                    viewPager!!.setBackgroundColor(
                            (argbEvaluator.evaluate(
                                    positionOffset,
                                    colors!![position],
                                    colors!![position + 1]
                            ) as Int)
                    )
                } else {
                    viewPager!!.setBackgroundColor(colors!![colors!!.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
package com.example.aggim.mypage.orderList
/*
Updated by Jin Lee on 2021/02/04
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.aggim.R
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.OrderListItemResponse
import com.example.aggim.api.response.OrderResponse
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.common.Prefs
import com.example.aggim.mypage.cart.RecyclerAdapterItems
import com.example.aggim.product.detail.ProductDetailViewModel
import kotlinx.android.synthetic.main.activity_my_page_main.*
import kotlinx.android.synthetic.main.activity_my_page_main.toolbar
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.activity_stamp.*
import kotlinx.android.synthetic.main.order_list_item.*
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.error

class OrderListActivity : BaseActivity<OrderListViewModel>() {
    override val viewModelType= OrderListViewModel::class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "주문 목록"

        val adapter = OrderListAdapter(ArrayList<OrderListItem>())
        xml_recycler_view.adapter = adapter

        val viewModel=getViewModel()
        //val response = viewModel.getOrder()
        //val adapter_product = OrderListProductAdapter()
        //product_recyclerView.adapter = adapter_product

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
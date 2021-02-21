package com.example.aggim.mypage.order

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.aggim.R

/*
Updated by Jin Lee on 2021/02/04
 */

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.api.response.OrderListItemResponse
import com.example.aggim.api.response.OrderProductResponse
import com.example.aggim.mypage.orderList.OrderAdapter
import com.example.aggim.mypage.orderList.OrderListViewModel
import com.example.aggim.mypage.orderList.OrderListViewModelFactory

class OrderListActivity : AppCompatActivity() {
    private val newOrderActivityRequestCode = 1
    private val orderListViewModel by viewModels<OrderListViewModel> {
        OrderListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_list_activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_order)
        val orderAdapter = OrderAdapter {orderListItemResponse -> adapterOnClick(orderListItemResponse)}
        recyclerView.adapter = orderAdapter

        orderListViewModel.orderLiveData.observe(this, {
            it?.let{
                orderAdapter.submitList(it as MutableList<OrderListItemResponse>)
            }
        })
    }

    private fun adapterOnClick(order : OrderListItemResponse) {
        //기부 정보 디테일 activity
        Toast.makeText(this, "주문한 상품 정보: " + order.orderProducts.toString() , Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts flower into viewModel. */
        if (requestCode == newOrderActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
            }
        }
    }
}


//class OrderListActivity : AppCompatActivity() {
//    override val viewModelType= OrderListViewModel::class
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order_list)
//
//        setSupportActionBar(toolbar)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "주문 목록"
//
//        val adapter = OrderAdapter(ArrayList<OrderListItem>())
//        xml_recycler_view.adapter = adapter
//
//        //val viewModel=getViewModel()
//        //val response = viewModel.getOrder()
//        //val adapter_product = OrderListProductAdapter()
//        //product_recyclerView.adapter = adapter_product
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        item?.let {
//            when(item.itemId) {
//                android.R.id.home -> onBackPressed()
//                else -> {}
//            }
//        }
//        return true
//    }
//}
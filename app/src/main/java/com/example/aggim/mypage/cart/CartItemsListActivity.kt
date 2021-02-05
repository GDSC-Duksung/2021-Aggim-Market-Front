package com.example.aggim.mypage.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.ProductResponse

class CartItemsListActivity : AppCompatActivity() {
    private val newCartItemActivityRequestCode = 1
    val headerAdapter = CartHeaderAdapter()
    var sum = 0
    private val cartItemsListViewModel by viewModels<CartItemsListViewModel> {
        CartItemsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_cart)
        val cartItemsAdapter = CartItemsAdapter {productResponse -> adapterOnClick(productResponse)}
        val concatAdapter = ConcatAdapter(headerAdapter, cartItemsAdapter)

        recyclerView.adapter = concatAdapter

        cartItemsListViewModel.cartItemsLiveData.observe(this, {
            it?.let {
                cartItemsAdapter.submitList(it as MutableList<ProductResponse>)
                for (i in it) {
                    sum += i.price
                }
                headerAdapter.updateSum(sum)
            }
        })
    }

    private fun adapterOnClick(product: ProductResponse) {
        cartItemsListViewModel.removeCartItem(product)
        sum = sum-product.price
        headerAdapter.updateSum(sum)
//        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCartItemActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let { data ->

            }
        }
    }
}
package com.example.aggim.product

/*
    Created by Jin Lee on 2021/01/31
 */

import android.app.Application
import android.content.Intent
import com.example.aggim.mypage.cart.CartItemsListActivity
import com.example.aggim.mypage.order.OrderListActivity
import com.example.aggim.product.registration.ProductRegistrationActivity
import com.example.aggim.product.search.ProductSearchActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app: Application) : BaseViewModel(app) {
    fun openSearchActivity(keyword: String?) {
        keyword?.let {
            startActivity<ProductSearchActivity> {
                putExtra(ProductSearchActivity.KEYWORD, keyword)
            }
        } ?: toast("검색 키워드를 입력해주세요.")
    }

    //테스트용
    fun openRegistrationActivity() {
        startActivity<CartItemsListActivity> {
            //startActivity<CartItemsListActivity>{
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            //}
        }
    }

    fun openCartActivity() {
        startActivity<ProductRegistrationActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }
}
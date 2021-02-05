package com.example.aggim.product.search

/*
    Created by Jin Lee on 2021/01/31
 */

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.example.aggim.api.response.ProductListItemResponse
import com.example.aggim.product.detail.ProductDetailActivity
import com.example.aggim.product.list.ProductListItemDataSource
import com.example.aggim.product.list.ProductListPagedAdapter
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductSearchViewModel(app: Application):
    BaseViewModel(app),
    ProductListPagedAdapter.ProductLiveDataBuilder,
    ProductListPagedAdapter.OnItemClickListener{

    var keyword: String? = null
    val products = buildPagedList()

    override fun createDataSource()=
        ProductListItemDataSource(null,keyword)

    override fun onClickProduct(productId: Long?) {
        startActivity<ProductDetailActivity>{
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }
    }

}
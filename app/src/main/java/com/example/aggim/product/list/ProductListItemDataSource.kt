package com.example.aggim.product.list

/*
    Created by Jin Lee on 2021/01/31
 */

import androidx.paging.PageKeyedDataSource
import com.example.aggim.App
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.ProductListItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class ProductListItemDataSource(
    private val categoryId: Int?,
    private val keyword: String? = null
) : PageKeyedDataSource<Long, ProductListItemResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(Long.MAX_VALUE, NEXT)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(params.key, NEXT)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(params.key, PREV)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    private fun getProducts(id: Long, direction: String) = runBlocking {
        try {
            AggimApi.instance.getProducts(id, categoryId, direction, keyword)
        } catch (e: Exception) {
            ApiResponse.error<List<ProductListItemResponse>>(
                "로그인이 필요합니다."
            )
        }
    }

    private fun showErrorMessage(
        response: ApiResponse<List<ProductListItemResponse>>
    ) {
        App.instance.toast(
            response.message ?: "로그인이 필요합니다."
        )
    }

    companion object {
        private const val NEXT = "next"
        private const val PREV = "prev"
    }
}
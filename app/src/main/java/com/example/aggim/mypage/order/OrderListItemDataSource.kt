package com.example.aggim.mypage.order

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.aggim.App
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.OrderListItemResponse
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class OrderListItemDataSource(resources: Resources) {
    private val initialOrderList = getOrders().data
    private val ordersLiveData = MutableLiveData(initialOrderList)

    fun getOrderList() : MutableLiveData<List<OrderListItemResponse>?> {
        Log.i("warning message", ordersLiveData.value.toString())
        return ordersLiveData
    }

    private fun getOrders() = runBlocking {
        try{
            Log.i("2222warning message", AggimApi.instance.getOrders().data.toString())
            AggimApi.instance.getOrders()
        } catch (e: Exception) {
            ApiResponse.error<List<OrderListItemResponse>>(
                    "주문 목록을 가져오면서 오류가 발생했습니다."
            )
        }
    }

    private fun showErrorMessage(
            response: ApiResponse<List<OrderListItemResponse>>
    ) {
        App.instance.toast(
                response.message ?: "알수없는 오류가 발생했습니다."
        )
    }

    companion object {
        private var INSTANCE: OrderListItemDataSource? = null

        fun getDataSource(resources: Resources): OrderListItemDataSource {
            return synchronized(OrderListItemDataSource::class) {
                val newInstance = INSTANCE ?: OrderListItemDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
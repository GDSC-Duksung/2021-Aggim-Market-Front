package com.example.aggim.mypage.cart

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.common.Prefs
import com.example.aggim.donation.DonateListItemDataSource
import kotlinx.coroutines.runBlocking

class CartItemListDataSource(resources: Resources) {
    private val initialCartItemList = Prefs.cList
    private val cartItemsLiveData = MutableLiveData(initialCartItemList)

    fun getCartItemList() : MutableLiveData<MutableList<ProductResponse>> {
        return cartItemsLiveData
    }

    fun removeCartItem(cartItem: ProductResponse) {
        val currentList = cartItemsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(cartItem)
            cartItemsLiveData.postValue(updatedList)
        }
    }

    fun getCartItemForId(id: Long): ProductResponse? {
        cartItemsLiveData.value?.let {cartItems->
            return cartItems.firstOrNull{ it.id == id}
        }
        return null
    }


    companion object {
        private var INSTANCE: CartItemListDataSource? = null

        fun getDataSource(resources: Resources): CartItemListDataSource {
            return synchronized(CartItemListDataSource::class) {
                val newInstance = INSTANCE ?: CartItemListDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }

    }
}
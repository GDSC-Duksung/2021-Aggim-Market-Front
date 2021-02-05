package com.example.aggim.mypage.cart

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.donation.DonateListItemDataSource
import com.example.aggim.donation.DonatesListViewModel

class CartItemsListViewModel(
    val dataSource: CartItemListDataSource
) : ViewModel() {
    val cartItemsLiveData = dataSource.getCartItemList()

    fun getCartItemForId(id: Long) : ProductResponse? {
        return dataSource.getCartItemForId(id)
    }

    fun removeCartItem(cartItem: ProductResponse) {
        dataSource.removeCartItem(cartItem)
    }
}

class CartItemsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartItemsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartItemsListViewModel(
                dataSource = CartItemListDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
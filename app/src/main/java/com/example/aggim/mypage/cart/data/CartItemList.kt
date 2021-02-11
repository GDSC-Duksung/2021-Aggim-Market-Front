package com.example.aggim.mypage.cart.data

import android.content.res.Resources

class CartItemList(
        val cList: List<CartItem> = emptyList<CartItem>()
) {
    fun getCartItem(idx: Int) : CartItem {
        return cList.get(idx)
    }
    fun cartItemList(resources: Resources): List<CartItem> {
        return cList
    }
}
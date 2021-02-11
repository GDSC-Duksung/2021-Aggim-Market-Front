package com.example.aggim.mypage.cart.data

import androidx.lifecycle.MutableLiveData

data class CartItem(
        var id: Long,
        var name: MutableLiveData<String>,
        var price: MutableLiveData<String>,
        var imagePaths: MutableLiveData<MutableList<String>>
)
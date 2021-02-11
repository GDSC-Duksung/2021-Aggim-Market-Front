package com.example.aggim.mypage.orderList

import com.example.aggim.api.response.ProductResponse

data class OrderListItem(
    val id: Long,
    val userId: Long,
    val products: MutableList<ProductResponse>,
    val createdAt: String,
    val price: Int//총 결제 금액
)
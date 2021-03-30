package com.example.aggim.api.response

data class OrderListItemResponse(
    val id: Long,
    val name: String,
    val userId: Long,
    val orderProducts: MutableList<OrderProductResponse>
)
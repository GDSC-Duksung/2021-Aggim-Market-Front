package com.example.aggim.api.response

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val orderProducts: MutableList<OrderProductResponse>
)
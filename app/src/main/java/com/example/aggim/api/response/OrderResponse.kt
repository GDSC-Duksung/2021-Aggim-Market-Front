package com.example.aggim.api.response

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val products: MutableList<ProductResponse>
)
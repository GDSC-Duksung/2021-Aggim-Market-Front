package com.example.aggim.api.response

data class OrderListItemResponse(
    val id: Long,
    val userId: Long,
    val products: MutableList<ProductResponse>
)
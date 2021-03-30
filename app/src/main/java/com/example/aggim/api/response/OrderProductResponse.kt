package com.example.aggim.api.response

data class OrderProductResponse(
        val id: Long,
        val product: ProductResponse,
        val productId: Long,
        val orderId:Long
)
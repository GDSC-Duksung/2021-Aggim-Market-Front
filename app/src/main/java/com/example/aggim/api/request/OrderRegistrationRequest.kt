package com.example.aggim.api.request

import com.example.aggim.api.response.ProductResponse

data class OrderRegistrationRequest(
    val productIds: MutableList<ProductResponse>,
    val name: String
)
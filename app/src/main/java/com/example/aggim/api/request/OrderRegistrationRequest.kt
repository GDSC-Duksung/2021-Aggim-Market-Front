package com.example.aggim.api.request

data class OrderRegistrationRequest(
    val productIds: List<Long>,
    val name: String
)
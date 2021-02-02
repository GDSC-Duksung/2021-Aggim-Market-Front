/*
    Created by Jin Lee on 2021/01/31
 */

package com.example.aggim.api.response

data class ProductListItemResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val status: String,
    val sellerId: Long,
    val imagePaths: List<String>
)
/*
    Created by Jin Lee on 2021/01/31
 */
package com.example.aggim.product.category

data class Category(
    val id: Int,
    val name: String
)

val categoryList = listOf(
    Category(0, "PANTRY"),
    Category(1, "MEAT"),
    Category(2, "FRUITS & VEGETABLES"),
    Category(3, "FROZEN"),
    Category(4,"BAKERY")
)
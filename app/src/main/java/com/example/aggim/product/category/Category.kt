/*
    Created by Jin Lee on 2021/01/31
 */
package com.example.aggim.product.category

data class Category(
    val id: Int,
    val name: String
)

val categoryList = listOf(
    Category(0, "즉석식품"),
    Category(1, "과자"),
    Category(2, "신선식품"),
    Category(3, "생활용품"),
    Category(4,"의류")
)
package com.example.aggim.api.response

/*
    Created by Seohyun Kim at 2021/01/21
 */

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null
) {

    companion object {
        inline fun <reified T> error(message: String? = null) =
            ApiResponse(false, null as T?, message)
    }

}
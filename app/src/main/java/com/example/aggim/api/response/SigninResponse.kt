package com.example.aggim.api.response

/*
    Created by Seohyun Kim at 2021/01/21
 */

data class SigninResponse (
        val token: String,
        val refreshToken: String,
        val userName: String,
        val userId: Long,
        val email: String
)
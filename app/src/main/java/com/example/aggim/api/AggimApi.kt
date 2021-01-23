package com.example.aggim.api

import com.example.aggim.api.request.SigninRequest
import com.example.aggim.api.request.SignupRequest
import com.example.aggim.api.response.*
import retrofit2.http.*

/*
    Created by Seohyun Kim at 2021/01/21
 */

interface AggimApi {
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest)
        : ApiResponse<Void>

    @POST("api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest)
        : ApiResponse<SigninResponse>

    companion object {
        val instance = ApiGenerator()
            .generate(AggimApi::class.java)
    }
}
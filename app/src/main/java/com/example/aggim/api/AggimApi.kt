package com.example.aggim.api

import com.example.aggim.api.request.SignupRequest
import com.example.aggim.api.response.*
import retrofit2.http.*

interface AggimApi {
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest)
        : ApiResponse<Void>

    companion object {
        val instance = ApiGenerator()
            .generate(AggimApi::class.java)
    }
}
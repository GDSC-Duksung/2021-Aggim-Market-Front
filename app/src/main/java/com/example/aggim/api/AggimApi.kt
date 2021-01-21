package com.example.aggim.api

import com.example.aggim.api.response.*
import retrofit2.http.*

interface AggimApi {
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    companion object {
        val instance = ApiGenerator()
            .generate(AggimApi::class.java)
    }
}
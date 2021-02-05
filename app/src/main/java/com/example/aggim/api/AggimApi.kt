package com.example.aggim.api

import com.example.aggim.api.request.DonateRegistrationRequest
import com.example.aggim.api.request.ProductRegistrationRequest
import com.example.aggim.api.request.SigninRequest
import com.example.aggim.api.request.SignupRequest
import com.example.aggim.api.response.*
import okhttp3.MultipartBody
//import okhttp3.Response
import retrofit2.http.*
import retrofit2.Response

/*
    Created by Seohyun Kim at 2021/01/21
    Updated by Jin Lee at 2021/02/01
 */

interface AggimApi {
    // 계정 관련
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest)
        : ApiResponse<Void>

    @POST("api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest)
        : ApiResponse<SigninResponse>

    // 상품 관련
   @Multipart
    @POST("/api/v1/product_images")
    suspend fun uploadProductImage(
            @Part images: MultipartBody.Part
    ): ApiResponse<ProductImageUploadResponse>

    @POST("/api/v1/products")
    suspend fun registerProduct(
        @Body request: ProductRegistrationRequest
    ): ApiResponse<Response<Void>>

    @GET("api/v1/products")
    suspend fun getProducts(
        @Query("productId") productId:Long,
        @Query("categoryId") categoryId:Int?,
        @Query("direction") direction:String, //prev,next
        @Query("keyword") keyword: String? = null
    ): ApiResponse<List<ProductListItemResponse>>

    @GET("/api/v1/products/{id}")
    suspend fun getProduct(@Path("id") id: Long)
            : ApiResponse<ProductResponse>

    // 기부 관련
    @POST("/api/v1/donates")
    suspend fun registerDonates(
            @Body request: DonateRegistrationRequest
    ): ApiResponse<Response<Void>>

    @GET("/api/v1/donates")
    suspend fun getDonates(): ApiResponse<List<DonateListItemResponse>>

    @GET("/api/v1/donates/{id}")
    suspend fun getDonate(@Path("id") id: Long)
        : ApiResponse<DonateResponse>

    companion object {
        val instance = ApiGenerator()
            .generate(AggimApi::class.java)
    }
}
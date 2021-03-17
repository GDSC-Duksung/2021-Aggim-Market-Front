package com.example.aggim.product.registration
/*
    Created by Jin Lee at 2021/01/31
 */
import com.example.aggim.api.AggimApi
import com.example.aggim.api.request.ProductRegistrationRequest
import com.example.aggim.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Response

class ProductRegistrar : AnkoLogger{

    suspend fun register(request: ProductRegistrationRequest) = when{
        request.isNotValidName ->
            ApiResponse.error("Enter the product name according to the conditions.")
        request.isNotValidDescription ->
            ApiResponse.error("Enter the product description according to the conditions.")
        request.isNotValidPrice ->
            ApiResponse.error("Enter the price according to the conditions.")
        request.isNotValidCategoryId ->
            ApiResponse.error("Select a category ID.")
        //request.isNotValidImageIds ->
            //ApiResponse.error("Register at least one image.")
        else -> withContext(Dispatchers.IO) {
            try {
                AggimApi.instance.registerProduct(request)
            } catch (e: Exception) {
                error("Product registration error.", e)
                ApiResponse.error<Response<Void>>(
                    "An unknown error has occurred."
                )
            }
        }
    }
}
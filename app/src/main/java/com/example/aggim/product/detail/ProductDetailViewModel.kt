package com.example.aggim.product.detail

/*
    Created by Jin Lee on 2021/01/31
 */

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.common.Prefs
import com.example.aggim.product.ProductStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.databinding.addAll
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.text.NumberFormat

class ProductDetailViewModel(app: Application) : BaseViewModel(app) {

    var productId: Long? = null

    val productName = MutableLiveData("-")
    val description = MutableLiveData("")
    val price = MutableLiveData("-")
    val imageUrls: MutableLiveData<MutableList<String>> =
        MutableLiveData(mutableListOf())

    fun loadDetail(id: Long) = viewModelScope.launch(Dispatchers.Main) {
        try {
            productId = id
            val response = getProduct(id)
            if (response.success && response.data != null) {
                updateViewData(response.data)
            } else {
                toast(response.message ?: "An unknown error has occurred.")
            }
        } catch (e: Exception) {
            toast(e.message ?: "An unknown error has occurred.")
        }
    }

    private suspend fun getProduct(id: Long) = try {
        AggimApi.instance.getProduct(id)
    } catch (e: Exception) {
        error("An error occurred while loading product information.", e)
        ApiResponse.error<ProductResponse>(
            "An error occurred while loading product information."
        )
    }

    private fun updateViewData(product: ProductResponse) {
        val commaSeparatedPrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if(ProductStatus.SOLD_OUT == product.status) "(SOLDOUT)" else ""

        productName.value = product.name
        description.value = product.description
        price.value =
            "₩${commaSeparatedPrice} $soldOutString"
        imageUrls.addAll(product.imagePaths)
    }

    suspend fun openInquiryActivity() {
        val resp = productId?.let { getProduct(it) }
        productId?.let {
            if (resp != null) {
                resp.data?.let { it -> Prefs.cList.add(it) }
            }
        }
        toast("Added to cart.")
    }
}
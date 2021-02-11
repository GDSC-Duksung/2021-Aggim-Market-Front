package com.example.aggim.mypage.orderList

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

class OrderListViewModel(app: Application) : BaseViewModel(app) {

    var productId: Long? = null

    val productName = MutableLiveData("-")
    val description = MutableLiveData("")
    val price = MutableLiveData("-")
    val imageUrls: MutableLiveData<MutableList<String>> =
        MutableLiveData(mutableListOf())
    suspend fun getOrder(id: Long) = AggimApi.instance.getOrders(id)
    fun loadDetail(id: Long) = viewModelScope.launch(Dispatchers.Main) {
        try {
            productId = id
            val response = getProduct(id)
            if (response.success && response.data != null) {
                updateViewData(response.data)
            } else {
                toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        } catch (e: Exception) {
            toast(e.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

    private suspend fun getProduct(id: Long) = try {
        AggimApi.instance.getProduct(id)
    } catch (e: Exception) {
        error("상품 정보를 가져오는 중 오류 발생.", e)
        ApiResponse.error<ProductResponse>(
            "상품 정보를 가져오는 중 오류가 발생했습니다."
        )
    }

    private fun updateViewData(product: ProductResponse) {
        val commaSeparatedPrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if(ProductStatus.SOLD_OUT == product.status) "(품절)" else ""

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
                resp.data?.let { it1 -> Prefs.cList.add(it1) }
            }
        }
        toast("장바구니에 담겼습니다.")
    }
}
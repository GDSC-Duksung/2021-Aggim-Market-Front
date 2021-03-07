package com.example.aggim.mypage.buy

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aggim.api.AggimApi
import com.example.aggim.api.request.DonateRegistrationRequest
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.donation.DonateListItemDataSource
import com.example.aggim.donation.DonatesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class BuyProductViewModel : ViewModel(){
    suspend fun registerDonate(value: Int, id: Int) {
        val request = DonateRegistrationRequest(value, id)
        try {
            val response = requestDonate(request)
        } catch (e: Exception) {

        }
    }

    private suspend fun requestDonate(request: DonateRegistrationRequest) =
        withContext(Dispatchers.IO) {
            AggimApi.instance.registerDonates(request)
        }

}

class BuyProductViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuyProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BuyProductViewModel(

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
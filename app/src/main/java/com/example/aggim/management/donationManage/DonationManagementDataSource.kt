package com.example.aggim.management.donationManage

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.aggim.App
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.DonateListItemResponse
import com.example.aggim.api.response.DonateResponse
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class DonationManagementDataSource(resources: Resources) {
    private val initialDonationList = getDonations().data
    private val donationsLiveData = MutableLiveData(initialDonationList)

    fun getDonationList() : MutableLiveData<List<DonateResponse>?> {
        Log.i("warning message", donationsLiveData.value.toString())
        return donationsLiveData
    }

    private fun getDonations() = runBlocking {
        try {
            AggimApi.instance.getDonations()
        } catch(e: Exception) {
            ApiResponse.error<List<DonateResponse>>(
                "알 수 없는 오류가 발생했습니다."
            )
        }
    }
    fun donateTo(donaition: DonateResponse) = runBlocking {
        try {
            AggimApi.instance.donateTo(donaition.donationId)
        } catch(e: Exception) {
            ApiResponse.error<List<DonateResponse>>(
                "알 수 없는 오류가 발생했습니다."
            )
        }
    }

    private fun showErrorMessage(
        response: ApiResponse<List<DonateListItemResponse>>
    ) {
        App.instance.toast(
            response.message ?: "알 수 없는 오류가 발생했습니다."
        )
    }

    companion object {
        private var INSTANCE: DonationManagementDataSource? = null

        fun getDataSource(resources: Resources): DonationManagementDataSource {
            return synchronized(DonationManagementDataSource::class) {
                val newInstance = INSTANCE ?: DonationManagementDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
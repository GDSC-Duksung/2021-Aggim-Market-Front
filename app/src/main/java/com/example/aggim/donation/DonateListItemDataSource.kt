
package com.example.aggim.donation

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.aggim.App
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ApiResponse
import com.example.aggim.api.response.DonateListItemResponse
import com.example.aggim.api.response.DonateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class DonateListItemDataSource(resources: Resources) {
    private val initialDonateList = getDonates().data
    private val donatesLiveData = MutableLiveData(initialDonateList)
    private val initialDonationList = getDonations().data
    private val donationsLiveData = MutableLiveData(initialDonationList)

    fun getDonateList() : MutableLiveData<List<DonateListItemResponse>?> {
        Log.i("warning message", donatesLiveData.value.toString())
        return donatesLiveData
    }
    fun getDonationList() : MutableLiveData<List<DonateResponse>?> {
        Log.i("warning message", donationsLiveData.value.toString())
        return donationsLiveData
    }

    private fun getDonates() = runBlocking {
        try {
            AggimApi.instance.getDonates()
        } catch (e: Exception) {
            ApiResponse.error<List<DonateListItemResponse>>(
                    "알 수 없는 오류가 발생했습니다."
            )
        }
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

    private fun showErrorMessage(
            response: ApiResponse<List<DonateListItemResponse>>
    ) {
        App.instance.toast(
                response.message ?: "알 수 없는 오류가 발생했습니다."
        )
    }

    companion object {
        private var INSTANCE: DonateListItemDataSource? = null

        fun getDataSource(resources: Resources): DonateListItemDataSource {
            return synchronized(DonateListItemDataSource::class) {
                val newInstance = INSTANCE ?: DonateListItemDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
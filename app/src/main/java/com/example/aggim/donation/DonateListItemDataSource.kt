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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class DonateListItemDataSource(resources: Resources) {
    private val initialDonateList = getDonates().data
    private val donatesLiveData = MutableLiveData(initialDonateList)

    fun getDonateList() : MutableLiveData<List<DonateListItemResponse>?> {
        Log.i("warning message", donatesLiveData.value.toString())
        return donatesLiveData
    }

//    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, DonateListItemResponse>) {
//        val response = getDonates()
//        if (response.success) {
//            response.data?.let{
//                if(it.isNotEmpty())
//                    callback.onResult(it, it.first().id, it.last().id)
//            }
//        } else {
//            GlobalScope.launch(Dispatchers.Main) {
//                showErrorMessage(response)
//            }
//        }
//    }
//
//    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, DonateListItemResponse>) {
//        val response = getDonates()
//        if (response.success) {
//            response.data?.let{
//                if(it.isNotEmpty())
//                    callback.onResult(it, it.last().id)
//            }
//        } else {
//            GlobalScope.launch(Dispatchers.Main) {
//                showErrorMessage(response)
//            }
//        }
//    }
//
//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, DonateListItemResponse>) {
//        val response = getDonates()
//        if (response.success) {
//            response.data?.let{
//                if(it.isNotEmpty())
//                    callback.onResult(it, it.first().id)
//            }
//        } else {
//            GlobalScope.launch(Dispatchers.Main) {
//                showErrorMessage(response)
//            }
//        }
//    }

    private fun getDonates() = runBlocking {
        try {
            AggimApi.instance.getDonates()
        } catch (e: Exception) {
            ApiResponse.error<List<DonateListItemResponse>>(
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
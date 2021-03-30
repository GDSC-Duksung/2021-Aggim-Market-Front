package com.example.aggim.management.donationManage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aggim.api.response.DonateResponse

class DonationManagementViewModel(
    val dataSource: DonationManagementDataSource
) : ViewModel() {
    val donationsLiveData = dataSource.getDonationList()
    fun donateTo(donaition: DonateResponse) {
        dataSource.donateTo(donaition)
    }
}

class DonationManagementViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DonationManagementViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DonationManagementViewModel(
                dataSource = DonationManagementDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
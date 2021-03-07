package com.example.aggim.management.donationManage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.DonateResponse


class DonationManagementActivity : AppCompatActivity() {
    private val donationManagementViewModel by viewModels<DonationManagementViewModel> {
        DonationManagementViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_management)

        val recyclerView: RecyclerView = findViewById(R.id.dmView)
        val donationItemsAdapter = DonationManagementAdapter {donateResponse -> adapterOnClick(donateResponse)}

        recyclerView.adapter = donationItemsAdapter

        donationManagementViewModel.donationsLiveData.observe(this, {
            it?.let {
                donationItemsAdapter.submitList(it as MutableList<DonateResponse>)
            }
        })
    }

    private fun adapterOnClick(donaition: DonateResponse) {
        //cartItemsListViewModel.removeCartItem(product)
//        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        //donationManagementViewModel.donateTo(donation)
        if(donaition.donatedVal >= donaition.goalVal) {
            donaition.donatedVal = 0
            Toast.makeText(this, "기부되었습니다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"모금액이 부족합니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.let { data ->

            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }
}
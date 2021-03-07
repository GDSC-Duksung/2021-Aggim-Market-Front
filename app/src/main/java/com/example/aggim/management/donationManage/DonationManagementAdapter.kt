package com.example.aggim.management.donationManage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.DonateResponse
import com.example.aggim.api.response.ProductResponse
import kotlinx.android.synthetic.main.cart_item_new.view.*
import kotlinx.android.synthetic.main.donation_management_item.view.*

class DonationManagementAdapter(
    private val onClick: (DonateResponse) -> Unit
) :
    ListAdapter<DonateResponse, DonationManagementAdapter.DonationManagementViewHolder>(DonationItemDiffCallback) {

    class DonationManagementViewHolder(
        itemView: View,
        val onClick: (DonateResponse) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val donationNameTextView: TextView = itemView.findViewById(R.id.dm_place)
        private val donationPercentTextView: TextView = itemView.findViewById(R.id.dm_percent)
        private var currentDonationItem: DonateResponse?=null
        var donationItemId: Long?= null

        init {
            itemView.donate_btn.setOnClickListener {
                currentDonationItem?.let{
                    onClick(it)
                }
            }
        }

        fun bind(donationItem: DonateResponse) {
            currentDonationItem = donationItem
            donationNameTextView.text = donationItem.name
            val pc = (donationItem.donatedVal*100/donationItem.goalVal)
            donationPercentTextView.text = (donationItem.donatedVal*100/donationItem.goalVal).toString() + "%"
            //donationPercentTextView.text = donationItem.donatedVal.toString()
            //if(pc >= 100) {
            //    itemView.donate_btn.setEnabled(true)
            //}
        }
    }

    override fun onBindViewHolder(holder: DonationManagementViewHolder, position: Int) {
        val donationItem = getItem(position)
        holder.bind(donationItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationManagementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.donation_management_item, parent, false)
        return DonationManagementViewHolder(view, onClick)
    }
}

object DonationItemDiffCallback : DiffUtil.ItemCallback<DonateResponse>() {
    override fun areItemsTheSame(oldItem: DonateResponse, newItem: DonateResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DonateResponse, newItem: DonateResponse): Boolean {
        return oldItem.donationId == newItem.donationId
    }
}
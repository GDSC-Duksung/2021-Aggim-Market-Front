package com.example.aggim.donation

import android.text.Layout
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.aggim.api.response.DonateListItemResponse
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R

class DonatesAdapter(private val onClick: (DonateListItemResponse) -> Unit) :
        ListAdapter<DonateListItemResponse, DonatesAdapter.DonateViewHolder>(DonateDiffCallback) {

    class DonateViewHolder(itemView: View, val onClick: (DonateListItemResponse) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
        private val donateTextView: TextView = itemView.findViewById(R.id.tv_donate)
        private var currentDonate: DonateListItemResponse?=null
        var donateId: Long? = null

        init {
            itemView.setOnClickListener {
                currentDonate?.let{
                    onClick(it)
                }
            }
        }

        fun bind(donate: DonateListItemResponse) {
            currentDonate = donate
            donateTextView.text = donate.id.toString()+"번째 기부! "+donate.donation.name+"에 "+donate.donatedVal.toString()+"원을 기부해주셨어요!"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonateViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.donate_item, parent, false)
        return DonateViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: DonateViewHolder, position: Int) {
        val donate = getItem(position)
        holder.bind(donate)
    }
}

object DonateDiffCallback : DiffUtil.ItemCallback<DonateListItemResponse>() {
    override fun areItemsTheSame(oldItem: DonateListItemResponse, newItem:DonateListItemResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DonateListItemResponse, newItem: DonateListItemResponse): Boolean {
        return oldItem.id == newItem.id
    }
}
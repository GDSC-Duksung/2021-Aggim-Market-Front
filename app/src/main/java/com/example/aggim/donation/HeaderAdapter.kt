package com.example.aggim.donation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.DonateListItemResponse
import kotlinx.android.synthetic.main.header_item.view.*

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var amountDonated: Int = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val amountImageView: ImageView = itemView.findViewById(R.id.img_donate)

        fun bind(amountDonated: Int) {
            Log.i("warning_message", "amountDonated: "+amountDonated)
            when (amountDonated) {
                in 0..1000-> {
                    amountImageView.setImageResource(R.drawable.donat1)
                }
                in 1000..5000-> {
                    amountImageView.setImageResource(R.drawable.donat2)
                }
                in 5000..10000 -> {
                    amountImageView.setImageResource(R.drawable.donat3)
                }
                in 10000..15000 -> {
                    amountImageView.setImageResource(R.drawable.donat4)
                }
                in 15000..30000 -> {
                    amountImageView.setImageResource(R.drawable.donat5)
                }
                in 30000..50000 -> {
                    amountImageView.setImageResource(R.drawable.donat6)
                }
                in 100000..150000 -> {
                    amountImageView.setImageResource(R.drawable.donat7)
                }
                in 150000..200000 -> {
                    amountImageView.setImageResource(R.drawable.donat8)
                }
                in 150000..200000 -> {
                    amountImageView.setImageResource(R.drawable.donat10)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderAdapter.HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1;
    }

    override fun onBindViewHolder(holder: HeaderAdapter.HeaderViewHolder, position: Int) {
        holder.bind(amountDonated)
    }

    fun updateAmountDonated(updateAmountDonated: Int) {
        amountDonated = updateAmountDonated
        notifyDataSetChanged()
    }
}
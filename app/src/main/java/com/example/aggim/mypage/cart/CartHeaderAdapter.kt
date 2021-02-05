package com.example.aggim.mypage.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import kotlinx.android.synthetic.main.cart_activity_main.view.*

class CartHeaderAdapter : RecyclerView.Adapter<CartHeaderAdapter.CartHeaderViewHolder>(){
    private var sum: Int = 0
    class CartHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val sumTextView: TextView = itemView.findViewById(R.id.tv_cart_sum)

        fun bind(sum: Int) {
            sumTextView.text = "총: " + sum +"₩"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHeaderAdapter.CartHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_header_item, parent, false)
        return CartHeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: CartHeaderViewHolder, position: Int) {
        holder.bind(sum)
    }

    fun updateSum(updateSum: Int) {
        sum = updateSum
        notifyDataSetChanged()
    }

}
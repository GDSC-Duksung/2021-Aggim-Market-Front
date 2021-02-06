package com.example.aggim.mypage.cart

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.mypage.buy.BuyProductActivity
import kotlinx.android.synthetic.main.cart_activity_main.view.*
import kotlinx.android.synthetic.main.cart_header_item.view.*

class CartHeaderAdapter(
    private val onClick: (Context) -> Unit
) : RecyclerView.Adapter<CartHeaderAdapter.CartHeaderViewHolder>() {
    private var sum: Int = 0

    class CartHeaderViewHolder(
        view: View,
        val onClick: (Context) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val sumTextView: TextView = itemView.findViewById(R.id.tv_cart_sum)
        private val btnBuyProduct: Button = itemView.findViewById(R.id.btn_buy)

        init {
            itemView.btn_buy.setOnClickListener {
                onClick(it.context)
            }
        }

        fun bind(sum: Int) {
            sumTextView.text = "총: " + sum +"₩"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHeaderAdapter.CartHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_header_item, parent, false)
        return CartHeaderViewHolder(view, onClick)
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
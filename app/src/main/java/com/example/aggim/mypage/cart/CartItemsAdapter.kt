package com.example.aggim.mypage.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.ProductResponse
import kotlinx.android.synthetic.main.cart_item_new.view.*

class CartItemsAdapter(
    private val onClick: (ProductResponse) -> Unit
) :
    ListAdapter<ProductResponse, CartItemsAdapter.CartItemViewHolder>(CartItemDiffCallback) {

    class CartItemViewHolder(
        itemView: View,
        val onClick: (ProductResponse) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.tv_cart_product_name)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.tv_cart_product_price)
        private var currentCartItem: ProductResponse?=null
        var cartItemId: Long?= null

        init {
            itemView.btn_cancel.setOnClickListener {
                currentCartItem?.let{
                    onClick(it)
                }
            }
        }

        fun bind(cartItem: ProductResponse) {
            currentCartItem = cartItem
            productNameTextView.text = cartItem.name
            productPriceTextView.text = cartItem.price.toString()+"₩"
        }
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_new, parent, false)
        return CartItemViewHolder(view, onClick)
    }
}

object CartItemDiffCallback : DiffUtil.ItemCallback<ProductResponse>() {
    override fun areItemsTheSame(oldItem: ProductResponse, newItem:ProductResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
        return oldItem.id == newItem.id
    }
}
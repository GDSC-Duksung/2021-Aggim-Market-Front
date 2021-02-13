package com.example.aggim.mypage.orderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.OrderListItemResponse
import com.example.aggim.api.response.OrderResponse
import com.example.aggim.api.response.ProductResponse
import kotlinx.android.synthetic.main.order_list_item_product.view.*

class OrderListProductAdapter(private val items: OrderResponse):
        RecyclerView.Adapter<OrderListProductAdapter.ViewHolder>(){

    override fun getItemCount(): Int = items.products.size

    override fun onBindViewHolder(holder: OrderListProductAdapter.ViewHolder, position: Int) {
        val item = items.products[position]
        holder.apply{
            bind(item, position)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderListProductAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item_product, parent, false)
        return OrderListProductAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View =v
        fun bind(item: ProductResponse, position: Int) {
            view.textView.text = item.name
            view.textView4.text = item.price.toString()
        }
    }
}
package com.example.aggim.mypage.orderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.common.Prefs
import com.example.aggim.databinding.ActivityBuyProductBinding.bind
import com.example.aggim.mypage.cart.RecyclerAdapterItems
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapter(private val items: ArrayList<OrderListItem>):
        RecyclerView.Adapter<OrderListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderListAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.order_list_item,
            parent, false)
        return OrderListAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bind(item, position)

            itemView.tag = item
        }
    }
        class ViewHolder(v:View) : RecyclerView.ViewHolder(v){
            private var view: View = v
            val btn = view.button
            fun bind(item: OrderListItem, position: Int){
                view.textView7.text = item.price.toString()
                view.textView8.text = item.createdAt
            }
        }
}
package com.example.aggim.mypage.orderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.response.OrderListItemResponse
import com.example.aggim.api.response.OrderProductResponse

class OrderAdapter(private val onClick: (OrderListItemResponse) -> Unit) :
        ListAdapter<OrderListItemResponse, OrderAdapter.OrderViewHolder>(OrderDiffCallback) {

    class OrderViewHolder(itemView: View, val onClick: (OrderListItemResponse) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
        private val orderTextView: TextView = itemView.findViewById(R.id.tv_order_number)
        private var currentOrder: OrderListItemResponse?=null

        init {
            itemView.setOnClickListener {
                currentOrder?.let{
                    onClick(it)
                }
            }
        }

        fun bind(order: OrderListItemResponse) {
            currentOrder = order
            orderTextView.text = "Order #"+ order.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item_header, parent, false)
        return OrderViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

}

object OrderDiffCallback : DiffUtil.ItemCallback<OrderListItemResponse>() {
    override fun areItemsTheSame(oldItem: OrderListItemResponse, newItem: OrderListItemResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OrderListItemResponse, newItem: OrderListItemResponse): Boolean {
        return oldItem.id == newItem.id
    }
}

//class OrderAdapter(private val items: ArrayList<OrderListItem>):
//        RecyclerView.Adapter<OrderAdapter.ViewHolder>(){
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): OrderAdapter.ViewHolder {
//        val inflatedView = LayoutInflater.from(parent.context).inflate(
//            R.layout.order_list_item,
//            parent, false)
//        return OrderAdapter.ViewHolder(inflatedView)
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
//        val item = items[position]
//        holder.apply {
//            bind(item, position)
//
//            itemView.tag = item
//        }
//    }
//        class ViewHolder(v:View) : RecyclerView.ViewHolder(v){
//            private var view: View = v
//            val btn = view.button
//            fun bind(item: OrderListItem, position: Int){
//                view.textView7.text = item.price.toString()
//                view.textView8.text = item.createdAt
//            }
//        }
//}
package com.example.aggim.mypage.cart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.aggim.R
import com.example.aggim.api.AggimApi
import com.example.aggim.api.response.ProductResponse
import com.example.aggim.common.Prefs
import com.example.aggim.data.CartItem
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.view.*
import kotlinx.android.synthetic.main.cart_item.view.*

class RecyclerAdapterItems(private val items: MutableList<ProductResponse>, private val ct: Activity) :
        RecyclerView.Adapter<RecyclerAdapterItems.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterItems.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.cart_item,
        parent, false)
        return RecyclerAdapterItems.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerAdapterItems.ViewHolder, position: Int) {
        val item = items[position]
        //val listener = View.OnClickListener {it->
         //   Toast.makeText(it.context, "Clicked:" + item.name, Toast.LENGTH_SHORT).show()
        //}
        holder.apply {
            bind(item, position)

            itemView.tag = item
        }
        holder.btn.xml_cit_button.setOnClickListener{
            Prefs.cList.removeAt(position)
            var sum = 0
            if(Prefs.cList.size > 0) {
                for(i in 0..Prefs.cList.size-1) {
                    sum += Prefs.cList.get(i).price
                }
            }
            if(sum > 0)
                ct.sumMoney.text = sum.toString()+"원"
            else
                ct.sumMoney.text = "0원"
            notifyDataSetChanged()
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val btn = view.xml_cit_button
        fun bind(item: ProductResponse, pos: Int) {
            //view.xml_cit_img_profile.setImageResource(item.imagePaths.)
            view.xml_cit_txt_name.text = item.name
            view.xml_cit_txt_price.text = item.price.toString()
        }

    }
}
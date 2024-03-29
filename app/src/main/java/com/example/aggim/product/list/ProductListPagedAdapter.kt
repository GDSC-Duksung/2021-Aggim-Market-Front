package com.example.aggim.product.list

/*
    Created by Jin Lee on 2021/01/31
 */

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aggim.api.ApiGenerator
import com.example.aggim.api.response.ProductListItemResponse
import com.example.aggim.common.paging.LiveDataPagedListBuilder
import com.example.aggim.product.ProductStatus
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.NumberFormat

class ProductListPagedAdapter(
    private val listener: OnItemClickListener
) : PagedListAdapter<ProductListItemResponse,
        ProductListPagedAdapter.ProductItemViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ProductItemViewHolder(parent, listener)

    override fun onBindViewHolder(
        holder: ProductItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class ProductItemViewHolder(
        parent: ViewGroup,
        private val listener: OnItemClickListener,
        private val ui: ProductListItemUI = ProductListItemUI()
    ) : RecyclerView.ViewHolder(
        ui.createView(AnkoContext.create(parent.context, parent))
    ) {

        var productId: Long? = null
        init {
            itemView.onClick { listener.onClickProduct(productId) }
        }

        fun bind(item: ProductListItemResponse?) = item?.let {
            this.productId = item.id
            val soldOutString =
                if(ProductStatus.SOLD_OUT == item.status) "(SOLDOUT)" else ""
            val commaSeparatedPrice =
                NumberFormat.getNumberInstance().format(item.price)

            ui.productName.text = item.name
            ui.price.text = "₩$commaSeparatedPrice $soldOutString"

            print("wow image paths = ${ApiGenerator.HOST}${item.imagePaths}")
            Glide.with(ui.imageView)
                    .load("${ApiGenerator.HOST}${item.imagePaths.firstOrNull()}")
                //.load("${ApiGenerator.HOST}/images/20210202/strawjam.jpg")
                .centerCrop()
                .into(ui.imageView)
        }
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ProductListItemResponse>() {
                override fun areItemsTheSame(
                    oldItem: ProductListItemResponse,
                    newItem: ProductListItemResponse
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ProductListItemResponse,
                    newItem: ProductListItemResponse
                ) = oldItem.toString() == newItem.toString()
            }
    }

    interface OnItemClickListener {
        fun onClickProduct(productId: Long?)
    }

    interface ProductLiveDataBuilder
        : LiveDataPagedListBuilder<Long, ProductListItemResponse>

}




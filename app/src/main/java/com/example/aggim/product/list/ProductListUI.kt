package com.example.aggim.product.list
/*
    Created by Jin Lee on 2021/01/31
 */

//실질적인 ProductListUI --> RecyclerView 하나만 존재

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductListUI (
    private val viewModel: ProductListViewModel
): AnkoComponent<ProductListFragment>{

    override fun createView(ui: AnkoContext<ProductListFragment>)=
        ui.verticalLayout{

            recyclerView{
                layoutManager = LinearLayoutManager(ui.ctx)
                adapter = ProductListPagedAdapter(viewModel)
                lparams(matchParent, matchParent)
                bindVisibility(ui.owner, viewModel.products){
                    it.isNotEmpty()
                }
                bindPagedList(
                    ui.owner,
                    ProductListPagedAdapter(viewModel),
                    viewModel.products
                )

            }
            textView("상품이 없습니다"){
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.products){
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent){
                gravity = Gravity.CENTER
            }
        }
}

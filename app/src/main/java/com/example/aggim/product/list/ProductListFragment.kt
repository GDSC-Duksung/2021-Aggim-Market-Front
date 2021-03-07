package com.example.aggim.product.list

/*
    Created by Jin Lee on 2021/01/31
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aggim.product.category.categoryList
import net.codephobia.ankomvvm.components.BaseFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import kotlin.reflect.KClass

class ProductListFragment : BaseFragment<ProductListViewModel>(){
    override val viewModelType = ProductListViewModel::class

    val categoryId get() = arguments?.getInt("categoryId")
            ?: throw IllegalStateException("categoryId missing")
    val title get() = arguments?.getString("title")
            ?: throw IllegalStateException("title missing")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState:Bundle?
    ) : View? {
        val viewModel = getViewModel()
        viewModel.categoryId = categoryId

        return ProductListUI(viewModel)
                .createView(AnkoContext.create(ctx, this))
    }

    companion object{
        fun newInstance(categoryId:Int, title:String) =
            ProductListFragment().apply {
                arguments = Bundle().also{
                    it.putInt("categoryId", categoryId)
                    it.putString("title", title)
                }
            }
    }

}




package com.example.aggim.product.list

/*
    Created by Jin Lee on 2021/01/31
 */

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.aggim.product.category.categoryList

class ProductListPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
){
    private val fragments = categoryList.map{
        ProductListFragment.newInstance(it.id, it.name)
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount()=fragments.size

    override fun getPageTitle(position:Int)=
        getItem(position).title
}

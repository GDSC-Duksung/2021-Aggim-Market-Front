package com.example.aggim.product

/*
    Created by Jin Lee on 2021/01/31
 */

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.aggim.R
import com.example.aggim.product.list.ProductListPagerAdapter
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView


class ProductMainActivity :
    BaseActivity<ProductMainViewModel>() {

    override val viewModelType = ProductMainViewModel::class

    private val ui by lazy { ProductMainUI(getViewModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        setupDrawerListener()

        ui.viewpager.adapter =
            ProductListPagerAdapter(supportFragmentManager)
        ui.tablayout.setupWithViewPager(ui.viewpager)
    }

    private fun setupDrawerListener() {
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }
}
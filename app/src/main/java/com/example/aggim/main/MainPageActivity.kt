package com.example.aggim.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.aggim.R
import com.example.aggim.R.drawable
import com.example.aggim.R.layout
import com.example.aggim.mypage.main.MyPageMain
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.main.*
import java.util.zip.Inflater

private const val NUM_PAGES = 3

class MainPageActivity() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(drawable.ic_menu_24px)

        main_navigationView.setNavigationItemSelectedListener(this)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home->{
                main_drawer_layout.openDrawer(GravityCompat.START)
            }
            R.id.item_mypage->{
                val nextIntent = Intent(this, MyPageMain::class.java)
                startActivity(nextIntent)
            }
        }
        return false
    }

    override fun onBackPressed() {
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}

package com.example.aggim.main

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.*
import android.view.View.TEXT_ALIGNMENT_GRAVITY
import android.view.View.inflate
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.aggim.R
import com.example.aggim.R.drawable
import com.example.aggim.R.layout
import com.example.aggim.common.Prefs
import com.example.aggim.mypage.main.MyPageMain
import com.example.aggim.mypage.stamp.StampActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.matchParent
import java.util.zip.Inflater

private const val NUM_PAGES = 3

class MainPageActivity() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(drawable.ic_menu_24px)

        var nav_header: LinearLayout = LayoutInflater.from(this).inflate(R.layout.main_drawer_header, null) as LinearLayout

        // 로그인 안했을 때
        if (Prefs.userId == null) {
            val signinBtn = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            signinBtn.layoutParams = layoutParams
            signinBtn.setText("로그인")

            val signupBtn = Button(this)
            signupBtn.layoutParams = layoutParams
            signupBtn.setText("회원가입")

            nav_header.addView(signinBtn)
            nav_header.addView(signupBtn)
        }

        // 로그인 했을 때
        else {
            val profile_img = ImageView(this)
            profile_img.setImageDrawable(resources.getDrawable(R.drawable.ic_face_24px))

            val tv_username = TextView(this)
            tv_username.setText(Prefs.userName + "님, 반가워요!")
            tv_username.setTextSize(17.0F)
            tv_username.setGravity(Gravity.CENTER)
            nav_header.addView(profile_img)
            nav_header.addView(tv_username)
        }

        main_navigationView.addHeaderView(nav_header)
        main_navigationView.setNavigationItemSelectedListener(this)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                main_drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_mypage->{
                if (Prefs.userId != null) {
                    val nextIntent = Intent(this, MyPageMain::class.java)
                    startActivity(nextIntent)
                } else {
                    Toast.makeText(this, "로그인 후 이용가능합니다",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.stamp->{
                if (Prefs.userId != null) {
                    val nextIntent = Intent(this, StampActivity::class.java)
                    startActivity(nextIntent)
                } else {
                    Toast.makeText(this, "로그인 후 이용가능합니다",Toast.LENGTH_SHORT).show()
                }
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

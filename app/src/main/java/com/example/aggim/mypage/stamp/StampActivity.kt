package com.example.aggim.mypage.stamp
/*
    Updated by Jin Lee on 2021/02/04
*/
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.aggim.R
import kotlinx.android.synthetic.main.activity_stamp.*

class StampActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp)

        //toolbar.setTitle("Stamp")
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Stamp"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F1F2F9")))
        supportActionBar?.setElevation(0f)

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }
}
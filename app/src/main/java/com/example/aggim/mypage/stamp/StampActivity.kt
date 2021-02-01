package com.example.aggim.mypage.stamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aggim.R
import kotlinx.android.synthetic.main.activity_stamp.*

class StampActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp)

        toolbar.setTitle("Stamp")
        setSupportActionBar(toolbar)
    }
}
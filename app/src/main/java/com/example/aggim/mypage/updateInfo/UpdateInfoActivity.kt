package com.example.aggim.mypage.updateInfo

/*
    Updated by Jin Lee on 2021/02/04
*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.aggim.R
import com.example.aggim.common.Prefs
import kotlinx.android.synthetic.main.activity_update_info.*
import kotlinx.android.synthetic.main.activity_update_info.toolbar

class UpdateInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_info)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        email.text = Prefs.email
        name.text = Prefs.userName

        editButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

            val dialogText = dialogView.findViewById<EditText>(R.id.edit)
            builder.setView(dialogView)
                .setPositiveButton("완료") {
                    dialogInterface, i ->
                    if(dialogText.text.toString() != "") {
                        Prefs.userName = dialogText.text.toString()
                        name.text = Prefs.userName
                    }
                }
                .setNegativeButton("취소") {
                    dialogInterface, i ->
                }
                .show()
        }

        completeButton.setOnClickListener {
            finish()
        }
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
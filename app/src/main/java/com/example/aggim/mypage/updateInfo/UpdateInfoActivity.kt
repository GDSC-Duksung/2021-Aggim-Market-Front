package com.example.aggim.mypage.updateInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.aggim.R
import com.example.aggim.common.Prefs
import kotlinx.android.synthetic.main.activity_my_page_main.*
import kotlinx.android.synthetic.main.activity_update_info.*

class UpdateInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_info)
        email.text = Prefs.userId.toString()
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
}
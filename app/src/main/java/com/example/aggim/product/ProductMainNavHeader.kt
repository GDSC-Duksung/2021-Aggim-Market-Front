package com.example.aggim.product

/*
    Created by Jin Lee on 2021/01/31
 */

import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.aggim.R
import com.example.aggim.common.Prefs
import com.example.aggim.signin.SigninActivity
import com.example.aggim.view.borderBottom
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ProductMainNavHeader : AnkoComponent<View> {
    override fun createView(ui: AnkoContext<View>) =
        ui.verticalLayout {
            padding = dip(20)
            background = borderBottom(width = dip(1))

            if (Prefs.userId == null) {
                button("로그인") {
                    textSize=20f
                }.lparams(matchParent, wrapContent)
                button("회원가") {
                    textSize=20f
                }.lparams(matchParent, wrapContent)
            } else {
                imageView(R.drawable.ic_face_24px)
                //.lparams(dip(54), dip(54))

                textView(Prefs.userName+"님 반가워요!") {
                    topPadding = dip(8)
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
            }
        }
}
package com.example.aggim.signup

import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.design.textInputEditText
import com.example.aggim.R
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/*
    Created by Seohyun Kim at 2021/01/21
 */

class SignupActivityUI(
private val viewModel: SignupViewModel
) : AnkoComponent<SignupActivity> {

    override fun createView(ui: AnkoContext<SignupActivity>) =
        ui.linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)


            textView("회원가입") {
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.colorPrimary
            }.lparams(width = matchParent) {
                bottomMargin = dip(50)
            }

            textInputLayout {
                textInputEditText {
                    hint = "Email"
                    setSingleLine()
                    bindString(ui.owner, viewModel.email)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }

            textInputLayout {
                textInputEditText {
                    hint = "Name"
                    setSingleLine()
                    bindString(ui.owner, viewModel.name)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }


            textInputLayout {
                textInputEditText {
                    hint = "Password"
                    setSingleLine()
                    inputType = InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }


            button("회원가입") {
                onClick { viewModel.signup() }
            }.lparams(width = matchParent)
        }

}

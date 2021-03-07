package com.example.aggim.product.registration

/*
    Created by Jin Lee at 2021/01/31
 */

import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.aggim.R
import com.example.aggim.api.ApiGenerator
import net.codephobia.ankomvvm.databinding.bindString
import net.codephobia.ankomvvm.databinding.bindStringEntries
import net.codephobia.ankomvvm.databinding.bindUrl
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

class ProductRegistrationUI(
    private val viewModel: ProductRegistrationViewModel
) : AnkoComponent<ProductRegistrationActivity>{

    override fun createView(
        ui: AnkoContext<ProductRegistrationActivity>
    ) = ui.scrollView {
        verticalLayout {
            padding = dip(20)
            clipToPadding = false
            backgroundColorResource = R.color.colorPrimary

            linearLayout{
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                pickImageView(ui,0)
                space().lparams(dip(5))
                pickImageView(ui,1)
                space().lparams(dip(5))
                pickImageView(ui,2)
                space().lparams(dip(5))
                pickImageView(ui,3)
                space().lparams(dip(5))
                pickImageView(ui,4)
            }

            textView("Product Info"){
                topPadding = dip(40)
                textSize=16f
                textColor=Color.BLACK
            }

            textInputLayout {
                topPadding = dip(20)
                textInputEditText {
                    hint = "Product Name"
                    setSingleLine()
                    bindString(ui.owner, viewModel.productName)
                    textChangedListener {
                        onTextChanged { _, _, _, _ ->
                            viewModel.checkProductNameLength()
                        }
                    }
                }

                textView("0/40") {
                    leftPadding = dip(4)
                    textSize = 12f
                    textColor = Color.BLACK
                    bindString(ui.owner, viewModel.productNameLength)
                }
            }

            textInputLayout {
                textInputEditText {
                    hint = "Product Description"
                    maxLines = 6
                    bindString(ui.owner, viewModel.description)
                    textChangedListener {
                        onTextChanged { _, _, _, _ ->
                            viewModel.checkDescriptionLength()
                        }
                    }
                }

                textView("0/500") {
                    leftPadding = dip(4)
                    textSize = 12f
                    textColor = Color.BLACK
                    bindString(ui.owner, viewModel.descriptionLength)
                }
            }

            textView("Category") {
                topPadding = dip(40)
                textSize = 16f
                textColor = Color.BLACK
            }

            verticalLayout {
                topPadding = dip(12)
                bottomPadding = dip(12)
                backgroundColor = 0xFFF1F2F9.toInt()
                spinner {
                    bindStringEntries(ui.owner, viewModel.categories)

                    onItemSelectedListener {
                        onItemSelected { _, _, position, _->
                            viewModel.onCategorySelect(position)
                        }
                    }
                }
            }.lparams(matchParent) {
                topMargin = dip(20)
            }

            textView("Price") {
                topPadding = dip(40)
                textSize = 16f
                textColor = Color.BLACK
            }

            textInputLayout {
                topPadding = dip(20)
                textInputEditText {
                    hint = "Ex) 1000"
                    setSingleLine()
                    inputType = InputType.TYPE_CLASS_NUMBER
                    bindString(ui.owner, viewModel.price)
                }
            }

            button("SAVE") {
                backgroundColorResource = R.color.colorButton
                textColor = Color.WHITE
                onClick { viewModel.register() }
            }.lparams(matchParent, wrapContent) {
                topMargin = dip(40)
            }
        }
    }

    private fun _LinearLayout.pickImageView(
        ui: AnkoContext<ProductRegistrationActivity>,
        imageNum: Int
    ) = imageView(R.drawable.icon_image){
        scaleType = ImageView.ScaleType.CENTER
        backgroundColor =  0xFFBFBFBF.toInt()

        onClick { viewModel.pickImage(imageNum) }
        bindUrl(ui.owner, viewModel.imageUrls[imageNum]){
            it?.let{
                scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this)
                    .load("${ApiGenerator.HOST}$it")
                    .centerCrop()
                    .into(this)
            }
        }
    }.lparams(dip(60), dip(60))
}


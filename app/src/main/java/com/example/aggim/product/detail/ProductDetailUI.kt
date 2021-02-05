package com.example.aggim.product.detail

/*
    Created by Jin Lee on 2021/01/31
 */

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.example.aggim.R
import net.codephobia.ankomvvm.databinding.bindItem
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.BOTTOM
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.TOP
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.viewPager

class ProductDetailUI (
    private val viewModel: ProductDetailViewModel
):AnkoComponent<ProductDetailActivity>{

    override fun createView(ui: AnkoContext<ProductDetailActivity>)=
        ui.constraintLayout{
            val content = scrollView{
                id = View.generateViewId()
                lparams(matchParent,0)

                verticalLayout {
                    constraintLayout {
                        lparams(matchParent, matchParent)
                        viewPager {
                            backgroundColor = Color.GREEN
                            adapter = ImageSliderAdapter().apply{
                            bindItem(ui.owner, viewModel.imageUrls) {
                                updateItems(it)
                        }
                    }
                }.lparams(matchParent, dip(0)) {
                        dimensionRatio = "1:1"
            }
        }
                verticalLayout {
                    padding = dip(20)

                    textView {
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.BLACK
                        bindString(ui.owner, viewModel.productName)
                    }.lparams(matchParent, wrapContent)

                    textView {
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.BLACK
                        bindString(ui.owner, viewModel.price)
                    }.lparams(matchParent, wrapContent) {
                        topMargin = dip(20)
                    }

                    textView {
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                        textColorResource = R.color.colorAccent
                    }.lparams(matchParent, wrapContent) {
                        topMargin = dip(20)
                    }

                    textView("상품설명") {
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent) {
                        topMargin = dip(20)
                    }
                }
            }
        }

    val fixedBar = linearLayout {
        id = View.generateViewId()
        padding = dip(10)
        gravity = Gravity.END
        backgroundColor = Color.DKGRAY
        lparams(matchParent, wrapContent)

        button("장바구니") {
            onClick { viewModel.openInquiryActivity() }
        }
        button("바로 구매"){
            onClick { viewModel.openInquiryActivity() }
        }
    }

    applyConstraintSet {
        fixedBar.id {
            connect(
                BOTTOM to BOTTOM of PARENT_ID
            )
        }

        content.id {
            connect(
                TOP to TOP of PARENT_ID,
                BOTTOM to TOP of fixedBar
            )
        }
    }
}
}
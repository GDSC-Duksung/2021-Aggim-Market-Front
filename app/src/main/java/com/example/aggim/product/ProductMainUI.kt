
package com.example.aggim.product

/*
    Created by Jin Lee on 2021/01/31
    Updated by Jin Lee on 2021/02/04
 */

import android.graphics.Color
import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View.generateViewId
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.aggim.R
import com.example.aggim.common.Prefs
import com.example.aggim.donation.DonatesListActivity
import com.example.aggim.management.donationManage.DonationManagementActivity
import com.example.aggim.mypage.main.MyPageMain
import com.example.aggim.mypage.stamp.StampActivity
import com.example.aggim.product.registration.ProductRegistrationActivity
import com.example.aggim.signin.SigninActivity
import com.example.aggim.view.borderBottom
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_FILL
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.viewPager

class ProductMainUI(
        private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>,
        NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager
    lateinit var toolBar: Toolbar

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
            ui.drawerLayout {
                drawerLayout = this

                frameLayout {
                    verticalLayout {
                        toolBar = toolbar {
                            title = "Aggim"
                            bottomPadding = dip(1)
                            background = borderBottom(width = dip(1))
                            menu.add("Search")
                                    .setIcon(R.drawable.icon_search)
                                    .setActionView(searchView {
                                        onQueryTextListener {
                                            onQueryTextSubmit { key ->
                                                viewModel.openSearchActivity(key)
                                                true
                                            }
                                        }
                                    })
                                    .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                        }.lparams(matchParent, wrapContent)

                        tablayout = themedTabLayout(
                                R.style.Tabtheme
                        ) {
                            bottomPadding = dip(1)
                            tabMode = MODE_SCROLLABLE
                            tabGravity = GRAVITY_FILL
                            background = borderBottom(width = dip(1))
                            lparams(matchParent, wrapContent)
                        }

                        viewpager = viewPager {
                            id = generateViewId()
                        }.lparams(matchParent, matchParent)
                    }

                    // 장바구니 버튼 (현재는 상품 등록 버튼)
                    // 상품 등록은 관리자로 로그인 되었을 시에만 보이게 할 예정
                    floatingActionButton {
                        backgroundColorResource = R.color.colorPrimaryDark
                        //imageResource = R.drawable.icon_cart_cutie
                        if(Prefs.userName == "admin") {
                            onClick { viewModel.openCartActivity()}
                        } else {
                            onClick { viewModel.openRegistrationActivity() }
                        }
                    }.lparams {
                        bottomMargin = dip(20)
                        marginEnd = dip(20)
                        gravity = Gravity.END or Gravity.BOTTOM
                    }
                }


                navigationView = navigationView {
                    ProductMainNavHeader()
                            .createView(AnkoContext.create(context, this))
                            .run(::addHeaderView)

                    menu.apply {
//                    add(NONE, MENU_ID_INQUIRY, NONE, "내 문의").apply {
//                        setIcon(R.drawable.ic_)
//                    }
                        if(Prefs.userName == "admin") {
                            add(NONE, MENU_ADMIN, NONE, "PRODUCT REGISTRATION").apply { // 추후 관리자 페이지로 연결 예
                                setIcon(R.drawable.ic_shopping_basket_24px)
                            }
                            add(NONE, MENU_DONATEMANAGEMENT, NONE, "DONATION MANAGEMENT").apply {
                                setIcon(R.drawable.ic_volunteer_activism_24px)
                            }
                        }
                        else {
                            add(NONE, MENU_ADMIN, NONE, "REGISTER PRODUCT").apply { // 추후 관리자 페이지로 연결 예
                                setIcon(R.drawable.ic_shopping_basket_24px)
                            }
                            add(NONE, MENU_MYPAGE, NONE, "MY PAGE").apply {
                                setIcon(R.drawable.ic_perm_contact_calendar_24px)
                            }
                            add(NONE, MENU_DONATE, NONE, "DONATION INFO").apply {
                                setIcon(R.drawable.ic_volunteer_activism_24px)
                            }
                            add(NONE, MENU_STAMP, NONE, "STAMP").apply {
                                setIcon(R.drawable.ic_approval_24px)
                            }
                        }
                        add(NONE, MENU_ID_LOGOUT, NONE, "LOG OUT").apply {
                            setIcon(R.drawable.ic_face_24px)

                        }
                    }
                    setNavigationItemSelectedListener(this@ProductMainUI)
                }.lparams(wrapContent, matchParent) {
                    gravity = Gravity.START
                }
            }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_MYPAGE -> {
                viewModel.startActivity<MyPageMain>()
            }
            MENU_DONATE -> {
                viewModel.startActivity<DonatesListActivity>()
            }
            MENU_STAMP -> {
                viewModel.startActivity<StampActivity>()
            }
            MENU_ADMIN -> {
                viewModel.startActivity<ProductRegistrationActivity>()
            }
            MENU_DONATEMANAGEMENT -> {
                viewModel.startActivity<DonationManagementActivity>()
            }
            MENU_ID_LOGOUT -> {
                Prefs.token = null
                Prefs.refreshToken = null
                viewModel.startActivityAndFinish<SigninActivity>()
            }
            // 문의 페이지는 추후 추가 예
            //MENU_ID_INQUIRY -> {
//          viewModel.startActivity<MyInquiryActivity>()
//          }
        }

        drawerLayout.closeDrawer(navigationView)

        return true
    }

    companion object {
        private const val MENU_MYPAGE = 1
        private const val MENU_DONATE = 2
        private const val MENU_STAMP = 3
        private const val MENU_ADMIN = 4
        private const val MENU_DONATEMANAGEMENT = 5
        private const val MENU_ID_LOGOUT = 6
    }

}
package com.example.aggim.product

/*
    Created by Jin Lee on 2021/01/31
    Updated by Jin Lee on 2021/02/04
 */

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View
import android.view.View.generateViewId
import android.view.View.inflate
import android.widget.LinearLayout
import android.widget.TableLayout
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.aggim.R
import com.example.aggim.common.Prefs
import com.example.aggim.donation.DonatesListActivity
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
import org.jetbrains.anko.design.tabLayout
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
                        backgroundColor = R.color.colorPrimary
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
                        R.style.Widget_MaterialComponents_TabLayout
                    ) {
                        bottomPadding = dip(1)
                        tabMode = MODE_SCROLLABLE
                        tabGravity = GRAVITY_FILL
                        background = borderBottom(width = dip(1))
                        backgroundColor = R.color.colorPrimary
                        lparams(matchParent, wrapContent)
                    }

                    viewpager = viewPager {
                        id = generateViewId()
                    }.lparams(matchParent, matchParent)
                }

                // 장바구니 버튼 (현재는 상품 등록 버튼)
                // 상품 등록은 관리자로 로그인 되었을 시에만 보이게 할 예정
                floatingActionButton {
                    imageResource = R.drawable.ic_shopping_basket_24px
                    onClick { viewModel.openRegistrationActivity() }
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
                    add(NONE, MENU_MYPAGE, NONE, "마이페이지").apply {
                        setIcon(R.drawable.ic_perm_contact_calendar_24px)
                    }
                    add(NONE, MENU_DONATE, NONE, "기부현황").apply {
                        setIcon(R.drawable.ic_volunteer_activism_24px)
                    }
                    add(NONE, MENU_STAMP, NONE, "스탬프").apply {
                        setIcon(R.drawable.ic_approval_24px)
                    }
                    add(NONE, MENU_ADMIN, NONE, "상품등록").apply { // 추후 관리자 페이지로 연결 예
                        setIcon(R.drawable.ic_shopping_basket_24px)
                    }
                    add(NONE, MENU_ID_LOGOUT, NONE, "로그아웃").apply {
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
        //private const val MENU_ID_INQUIRY = 1
        private const val MENU_ID_LOGOUT = 5
    }

}

/*
class ProductMainUI(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var toolBar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
        ui.drawerLayout {
            drawerLayout = this

            frameLayout {
                verticalLayout {
                    toolBar = toolbar {
                        title = "aggim"
                        menu.add("Search")
                            .setIcon(R.drawable.icon_search)
                            .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                        bottomPadding = dip(1)
                        background = borderBottom(width = dip(1))
                        /*menu.add("Search")
                            .setActionView(searchView {
                                onQueryTextListener {
                                    onQueryTextSubmit { key ->
                                        viewModel.openSearchActivity(key)
                                        true
                                    }
                                }
                            })
                            .setShowAsAction(SHOW_AS_ACTION_ALWAYS)*/
                    }.lparams(matchParent, wrapContent)

                    tablayout = themedTabLayout(
                        R.style.Widget_MaterialComponents_TabLayout
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

                }.lparams(matchParent, matchParent)

                //registration 확인용
//                floatingActionButton{
//                    imageResource = R.drawable.icon_search
//                    onClick { viewModel.openRegistrationActivity()}
//                }.lparams{
//                    bottomMargin=dip(20)
//                    marginEnd=dip(20)
//                    gravity=Gravity.END or Gravity.BOTTOM
//                }

                navigationView = navigationView {
                    ProductMainNavHeader()
                        .createView(AnkoContext.create(context, this))
                        .run(::addHeaderView)
                    menu.apply {
                        add(NONE, MENU_ID_LOGIN, NONE, "로그인").apply {
                            //setIcon(R.drawable)--if wanted
                        }
                        add(NONE, MENU_ID_JOIN, NONE, "회원가입").apply {
                            //setIcon(R.drawable)--if wanted
                        }
                        add(NONE, MENU_ID_CART, NONE, "장바구니").apply {
                            //setIcon(R.drawable)--if wanted
                        }
                        add(NONE, MENU_ID_MYPAGE, NONE, "마이페이지").apply {
                            //setIcon(R.drawable)--if wanted
                        }
                    }
                    setNavigationItemSelectedListener(this@ProductMainUI)
                }.lparams(matchParent, matchParent) {
                    gravity = Gravity.START
                }
            }
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_LOGIN -> {
                viewModel.toast("로그인")
            }
            MENU_ID_JOIN -> {
                viewModel.toast("회원가입")
            }
            MENU_ID_CART -> {
                viewModel.toast("장바구니")
            }
            MENU_ID_MYPAGE -> {
                viewModel.toast("마이페이지")
            }
        }

        drawerLayout.closeDrawer(navigationView)

        return true
    }

    companion object {
        private const val MENU_ID_LOGIN = 1
        private const val MENU_ID_JOIN = 2
        private const val MENU_ID_CART = 3
        private const val MENU_ID_MYPAGE = 4
    }
}
*/

    
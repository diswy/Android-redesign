package com.cqebd.student

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cqebd.student.databinding.ActivityMainBinding
import xiaofu.lib.base.activity.BaseBindActivity
import xiaofu.lib.inline.onClick

class MainActivity : BaseBindActivity<ActivityMainBinding>(), BottomNavigationBar.OnTabSelectedListener {

    private lateinit var homeUserFrag: Fragment

    override fun onTabReselected(position: Int) {}

    override fun onTabUnselected(position: Int) {}

    override fun onTabSelected(position: Int) {
        replaceFragments(position)
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initialize(binding: ActivityMainBinding) {
        homeUserFrag = ARouter.getInstance().build("/ebd/student/user/home").navigation() as Fragment

        binding.mainTab.addItem(BottomNavigationItem(R.drawable.main_tab_video, "首页").setActiveColor(Color.parseColor("#33e5e5")))
                .addItem(BottomNavigationItem(R.drawable.main_tab_mine, "我的").setActiveColor(Color.parseColor("#33e5e5")))
                .setBarBackgroundColor(R.color.colorPrimary)
                .initialise()
        binding.mainTab.selectTab(0)

        binding.mainTab.setTabSelectedListener(this)
        replaceFragments(0)
    }

    override fun bindListener(binding: ActivityMainBinding) {
        binding.btnGoLogin.onClick {
            ARouter.getInstance()
                    .build("/ebd/student/user/login")
                    .navigation()
        }
    }

    private fun replaceFragments(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when (position) {
                0 -> {
                    replace(R.id.home_frag_container, homeUserFrag)
                }
                1 -> {
                    replace(R.id.home_frag_container, homeUserFrag)
                }
//                2 -> replace(R.id.home_activity_frag_container, fragment3)
//                3 -> replace(R.id.home_activity_frag_container, fragment4)
//                4 -> replace(R.id.home_activity_frag_container, fragment5)
//                else -> replace(R.id.home_activity_frag_container, fragment6)
            }
        }.commitAllowingStateLoss()
    }

}

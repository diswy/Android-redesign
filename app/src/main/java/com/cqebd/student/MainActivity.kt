package com.cqebd.student

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cqebd.student.databinding.ActivityMainBinding
import cqebd.student.network.Status
import cqebd.student.viewmodel.EbdViewModel
import org.jetbrains.anko.info
import xiaofu.lib.BaseApp
import xiaofu.lib.base.activity.BaseBindActivity
import xiaofu.lib.inline.onClick

class MainActivity : BaseBindActivity<ActivityMainBinding>(), BottomNavigationBar.OnTabSelectedListener {

    override fun onTabReselected(position: Int) {}

    override fun onTabUnselected(position: Int) {}

    override fun onTabSelected(position: Int) {
        replaceFragments(position)
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initialize(binding: ActivityMainBinding) {
        binding.mainTab.addItem(BottomNavigationItem(R.drawable.main_tab_video, "首页").setActiveColor(Color.parseColor("#33e5e5")))
                .addItem(BottomNavigationItem(R.drawable.main_tab_mine, "我的").setActiveColor(Color.parseColor("#33e5e5")))
                .setBarBackgroundColor(R.color.colorPrimary)
                .initialise()
        binding.mainTab.selectTab(0)

        binding.mainTab.setTabSelectedListener(this@MainActivity)
        replaceFragments(0)

        val loginViewModel = ViewModelProviders.of(this, BaseApp.instance.factory).get(EbdViewModel::class.java)

        loginViewModel.login("xsc001", "123456")
                .observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            println("------ LOADING")
                            it.data
                        }
                        Status.SUCCESS -> {
                            println("------ SUCCESS")
                            println("------ SUCCESS data :${it.data}")
                        }
                        Status.ERROR -> {
                            println("------ ERROR")
                        }
                    }
                })

    }

    override fun bindListener(binding: ActivityMainBinding) {
        binding.btnGoLogin.onClick {
            ARouter.getInstance()
                    .build("/module_user/login")
                    .navigation()
        }
    }

    private fun replaceFragments(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when (position) {// 如果一开始将首页的所有fragment初始化，在切换的时候，会产生内存泄漏
                0 -> {
                    replace(R.id.home_frag_container, ARouter.getInstance()
                            .build("/module_video/home")
                            .navigation() as Fragment)
                }
                1 -> {
                    replace(R.id.home_frag_container, ARouter.getInstance()
                            .build("/module_user/home")
                            .navigation() as Fragment)
                }
            }
        }.commitAllowingStateLoss()
    }

}

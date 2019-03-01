package com.cqebd.student

import org.jetbrains.anko.startActivity
import xiaofu.lib.base.activity.BaseActivity
import xiaofu.lib.base.timer.ITimerEnd

class SplashActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun initialize() {

//        LottieCompositionFactory.fromJsonString("","test")
//                .addListener {
//
//                }
//                .addFailureListener {
//
//                }

        timer(1, object : ITimerEnd {
            override fun onTimeEnd() {
                startActivity<MainActivity>()
                finish()
            }
        })
    }

}

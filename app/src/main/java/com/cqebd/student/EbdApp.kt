package com.cqebd.student

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 * Created by @author xiaofu on 2018/12/15.
 */
class EbdApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.init(this)
    }
}
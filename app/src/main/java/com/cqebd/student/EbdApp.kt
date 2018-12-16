package com.cqebd.student

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kk.taurus.exoplayer.ExoMediaPlayer
import com.kk.taurus.playerbase.config.PlayerConfig
import com.kk.taurus.playerbase.config.PlayerLibrary
import com.squareup.leakcanary.LeakCanary
import com.kk.taurus.playerbase.record.PlayRecordManager


/**
 *
 * Created by @author xiaofu on 2018/12/15.
 */
class EbdApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this))
            return
        LeakCanary.install(this)

        ARouter.openDebug()
        ARouter.init(this)

        // Player初始化
        PlayerConfig.setUseDefaultNetworkEventProducer(true)
        PlayerLibrary.init(this)
        ExoMediaPlayer.init(this)
        //播放记录的配置
        //开启播放记录
        PlayerConfig.playRecord(true)
        PlayRecordManager.setRecordConfig(PlayRecordManager.RecordConfig.Builder()
                .setMaxRecordCount(100)
                .build())
    }
}
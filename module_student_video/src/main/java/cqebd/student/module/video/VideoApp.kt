package cqebd.student.module.video

import android.app.Application
import com.kk.taurus.exoplayer.ExoMediaPlayer
import com.kk.taurus.playerbase.config.PlayerConfig
import com.kk.taurus.playerbase.config.PlayerLibrary
import com.kk.taurus.playerbase.record.PlayRecordManager
import xiaofu.lib.BaseApp
import xiaofu.lib.base.IBaseApplication

/**
 *
 * Created by @author xiaofu on 2018/12/19.
 */
class VideoApp : IBaseApplication {

    override fun init(application: Application) {
        // Player初始化
        PlayerConfig.setUseDefaultNetworkEventProducer(true)
        PlayerLibrary.init(application)
        ExoMediaPlayer.init(application)
        //播放记录的配置
        //开启播放记录
        PlayerConfig.playRecord(true)
        PlayRecordManager.setRecordConfig(PlayRecordManager.RecordConfig.Builder()
                .setMaxRecordCount(100)
                .build())
    }
}
package cqebd.student.module.video.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.ActivityPlayerRecordBinding
import cqebd.student.viewmodel.VideoViewModel
import cqebd.student.vo.CourseInfo
import org.jetbrains.anko.dip
import xiaofu.lib.BaseApp
import xiaofu.lib.base.activity.BaseBindActivity
import xiaofu.lib.network.Status
import xiaofu.lib.player.view.Player

/**
 * 录播页面
 */
@Route(path = "/module_video/player_record")
class PlayerRecordActivity : BaseBindActivity<ActivityPlayerRecordBinding>() {

    @Autowired
    @JvmField
    var info: CourseInfo? = null

    private lateinit var model: VideoViewModel
    private lateinit var mPlayer: Player

    override fun isKeepScreenOn(): Boolean = true
    override fun isTranslucentMode(): Boolean = true

    override fun getLayoutRes(): Int = R.layout.activity_player_record

    override fun initialize(binding: ActivityPlayerRecordBinding) {
        ARouter.getInstance().inject(this)
        model = ViewModelProviders.of(this, BaseApp.instance.factory).get(VideoViewModel::class.java)

        mPlayer = binding.player

        lifecycle.addObserver(mPlayer)

        mPlayer.setToggleScreenListener(object : Player.ScreenChangeListener {
            override fun onScreenChange(isFull: Boolean) {
                requestedOrientation = if (isFull)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })

        if (info != null) {
            model.getCourseDetail(info!!.Id).observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        println("----->>>成功${it.data}")
                        mPlayer.play("http://1251918109.vod2.myqcloud.com/9f878384vodgzp1251918109/9d43eafc5285890784035597639/f0.mp4")
                    }
                    Status.ERROR -> {
                        handleExceptions(it.throwable)
                    }
                    Status.LOADING -> {
                    }
                }
            })
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            println("--->>>全屏幕切换 ture")
            updatePlayer(true)
        } else {
            println("--->>>全屏幕切换 false")
            updatePlayer(false)
        }

    }

    private fun updatePlayer(isFull: Boolean) {
        val lp: ConstraintLayout.LayoutParams = mPlayer.layoutParams as ConstraintLayout.LayoutParams
        if (isFull) {
            lp.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            lp.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        } else {
            lp.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            lp.height = dip(260)
        }
        mPlayer.layoutParams = lp
    }
}

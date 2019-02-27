package xiaofu.lib.player.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.kk.taurus.playerbase.assist.OnVideoViewEventHandler
import com.kk.taurus.playerbase.entity.DataSource
import com.kk.taurus.playerbase.receiver.ReceiverGroup
import com.kk.taurus.playerbase.widget.BaseVideoView
import xiaofu.component.player.R
import xiaofu.lib.player.cover.ControllerCover
import xiaofu.lib.player.cover.LoadingCover
import xiaofu.lib.player.play.DataInter

/**
 * BaseVideoView的二次封装
 * 声明为生命周期组件
 * Created by @author xiaofu on 2019/2/26.
 */
class Player @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), LifecycleObserver {

    private var screenListener: ScreenChangeListener? = null
    private var isFull = true

    interface ScreenChangeListener {
        fun onScreenChange(isFull: Boolean)
    }

    private var player: BaseVideoView
    private var receiveGroup:ReceiverGroup

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_component_player, this, true)
        player = view.findViewById(R.id.base_video_view)
        receiveGroup = ReceiverGroup()
    }

    //--------绑定生命周期--------
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumePlayer() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pausePlayer() {
        player.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopPlayer() {
        player.stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destoryPlayer() {
        player.stopPlayback()
    }

    //--------播放器的方法封装--------
    fun play(url: String) {
        val dataSource = DataSource(url)
        //-----添加播放器组件-----
//        layout_component_player.setOnPlayerEventListener { eventCode, bundle ->
//            //            println("--->>>代码： $eventCode   bundle: $bundle")
//        }
        receiveGroup.addReceiver("loading_cover", LoadingCover(context))
        receiveGroup.addReceiver("controller_cover", ControllerCover(context))
        player.setReceiverGroup(receiveGroup)
        player.setEventHandler(onVideoViewEventHandler)
        //-----添加播放器组件-----
        player.setDataSource(dataSource)
        player.start()
    }

    fun setToggleScreenListener(listener: ScreenChangeListener) {
        this.screenListener = listener
    }

    /**
     * 播放器数据通信
     * 默认播放暂停重播父类已实现，这里用于自定义的设置
     */
    private val onVideoViewEventHandler = object : OnVideoViewEventHandler() {
        override fun onAssistHandle(assist: BaseVideoView?, eventCode: Int, bundle: Bundle?) {
            super.onAssistHandle(assist, eventCode, bundle)
            when (eventCode) {
                DataInter.Event.EVENT_CODE_REQUEST_BACK -> {
                    println("--->>>我点击了按钮3")
//                    layout_component_player.setSpeed(1f)
                    screenListener?.onScreenChange(isFull)
                    receiveGroup.groupValue.putBoolean(DataInter.Key.KEY_IS_LANDSCAPE,isFull)
                    isFull = !isFull
                }
                DataInter.Event.EVENT_MULTIPLE_PLAY -> {
                    println("--->>>我点击了按钮4 倍数播放")
                    bundle?.let { b ->
                        val speed = b.getFloat(DataInter.Key.KEY_MULTIPLE_PLAY, 1f)
                        player.setSpeed(speed)
                    }
                }
            }
        }
    }
}
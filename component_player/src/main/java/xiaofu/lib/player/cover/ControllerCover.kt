package xiaofu.lib.player.cover

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.kk.taurus.playerbase.event.OnPlayerEventListener
import com.kk.taurus.playerbase.receiver.BaseCover
import com.kk.taurus.playerbase.receiver.IReceiverGroup
import com.kk.taurus.playerbase.touch.OnTouchGestureListener
import xiaofu.component.player.R
import xiaofu.lib.player.play.DataInter

private const val MSG_CODE_DELAY_HIDDEN_CONTROLLER = 101

class ControllerCover constructor(context: Context) : BaseCover(context), OnTouchGestureListener {
    private lateinit var mControllerTop: View// 控制面板顶部
    private lateinit var mControllerBottom: View// 控制面板底部
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    private var mGestureEnable: Boolean = true // 手势能否使用
    private var animatorTop: ObjectAnimator? = null// 淡入淡出动效
    private var animatorBottom: ObjectAnimator? = null// 淡入淡出动效

    override fun onReceiverBind() {// 绑定控件
        super.onReceiverBind()
        mControllerTop = view.findViewById(R.id.player_controller_container_top)
        mControllerBottom = view.findViewById(R.id.player_controller_container_bottom)

        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)

        btn1.setOnClickListener { requestResume(null) }
        btn2.setOnClickListener { requestPause(null) }
        btn3.setOnClickListener {
            notifyReceiverEvent(DataInter.Event.EVENT_CODE_REQUEST_BACK, null)
        }
        btn4.setOnClickListener {
            val bundle = Bundle()
            bundle.putFloat(DataInter.Key.KEY_MULTIPLE_PLAY, 2f)
            notifyReceiverEvent(DataInter.Event.EVENT_MULTIPLE_PLAY, bundle)
        }

        groupValue.registerOnGroupValueUpdateListener(mOnGroupValueUpdateListener)
    }

    override fun onReceiverUnBind() {// 释放资源
        super.onReceiverUnBind()
        groupValue.unregisterOnGroupValueUpdateListener(mOnGroupValueUpdateListener)
    }

    override fun onCoverAttachedToWindow() {
        super.onCoverAttachedToWindow()
//        mController.visibility = View.VISIBLE
    }

    override fun onCoverDetachedToWindow() {
        super.onCoverDetachedToWindow()
        removeDelayHiddenMessage()
    }

    override fun onPlayerEvent(eventCode: Int, bundle: Bundle?) {
        when (eventCode) {
            OnPlayerEventListener.PLAYER_EVENT_ON_BUFFERING_START,
            OnPlayerEventListener.PLAYER_EVENT_ON_DATA_SOURCE_SET,
            OnPlayerEventListener.PLAYER_EVENT_ON_PROVIDER_DATA_START,
            OnPlayerEventListener.PLAYER_EVENT_ON_SEEK_TO -> println("--->>>控制器日志：Loading开始")
            OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START -> println("--->>>控制器日志：开始播放了")
            OnPlayerEventListener.PLAYER_EVENT_ON_BUFFERING_END,
            OnPlayerEventListener.PLAYER_EVENT_ON_STOP,
            OnPlayerEventListener.PLAYER_EVENT_ON_PROVIDER_DATA_ERROR,
            OnPlayerEventListener.PLAYER_EVENT_ON_SEEK_COMPLETE -> sendDelayHiddenMessage()
        }
    }

    override fun onReceiverEvent(eventCode: Int, bundle: Bundle?) {
    }

    override fun onErrorEvent(eventCode: Int, bundle: Bundle?) {

    }

    override fun onCreateCoverView(context: Context?): View {
        return View.inflate(context, R.layout.layout_controller_cover, null)
    }

    override fun getCoverLevel(): Int {
        return levelLow(1)
    }

    private fun setGestureEnable(gestureEnable: Boolean) {
        this.mGestureEnable = gestureEnable
    }

    /**
     * 监听
     */
    private val mOnGroupValueUpdateListener = object : IReceiverGroup.OnGroupValueUpdateListener {
        override fun filterKeys(): Array<String> {
            return arrayOf(DataInter.Key.KEY_COMPLETE_SHOW,
                    DataInter.Key.KEY_TIMER_UPDATE_ENABLE,
                    DataInter.Key.KEY_DATA_SOURCE,
                    DataInter.Key.KEY_IS_LANDSCAPE,
                    DataInter.Key.KEY_CONTROLLER_TOP_ENABLE)
        }

        override fun onValueUpdate(key: String, value: Any) {
            println("--->>> KEY = $key ，VALUE = $value")
            when (key) {
                DataInter.Key.KEY_COMPLETE_SHOW -> {
                    val show = value as Boolean
                    if (show) {
//                        setControllerState(false)
                    }
//                    setGestureEnable(!show)
                }
                DataInter.Key.KEY_CONTROLLER_TOP_ENABLE -> {
//                    mControllerTopEnable = value as Boolean
//                    if (!mControllerTopEnable) {
//
//                    }
                }
                DataInter.Key.KEY_IS_LANDSCAPE -> {
                }
                DataInter.Key.KEY_TIMER_UPDATE_ENABLE -> {
                }
                DataInter.Key.KEY_DATA_SOURCE -> {
                }
            }
        }
    }

    //--------控制器手势事件--------
    override fun onEndGesture() {

    }

    override fun onSingleTapUp(event: MotionEvent?) {
        println("--->>>onSingleTapUp  点一下 走一次")
        toggleController()
    }

    override fun onDown(event: MotionEvent?) {

    }

    override fun onDoubleTap(event: MotionEvent?) {
        println("--->>>onDoubleTap  双击了屏幕")
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float) {

    }

    //--------控制器延时隐藏--------
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_CODE_DELAY_HIDDEN_CONTROLLER -> {
                    setControllerState(false)
                }
            }
        }
    }

    private fun sendDelayHiddenMessage() {
        removeDelayHiddenMessage()
        mHandler.sendEmptyMessageDelayed(MSG_CODE_DELAY_HIDDEN_CONTROLLER, 5000)
    }

    private fun removeDelayHiddenMessage() {
        mHandler.removeMessages(MSG_CODE_DELAY_HIDDEN_CONTROLLER)
    }

    //--------控制器状态、动效处理--------
    private fun isControllerShow(): Boolean {
        return mControllerBottom.visibility == View.VISIBLE
    }

    private fun toggleController() {
        setControllerState(!isControllerShow())
    }

    private fun cancelAnimation() {
        animatorTop?.cancel()
        animatorTop?.removeAllListeners()
        animatorTop?.removeAllUpdateListeners()

        animatorBottom?.cancel()
        animatorBottom?.removeAllListeners()
        animatorBottom?.removeAllUpdateListeners()
    }

    private fun setControllerState(state: Boolean) {
        if (state) {
            sendDelayHiddenMessage()
        } else {
            removeDelayHiddenMessage()
        }
        setControllerViewState(state)
    }

    private fun setControllerViewState(show: Boolean) {
        mControllerTop.clearAnimation()
        mControllerBottom.clearAnimation()
        cancelAnimation()

        animatorTop = ObjectAnimator.ofFloat(mControllerTop, "alpha",
                if (show) 0f else 1f, if (show) 1f else 0f).setDuration(300)
        animatorBottom = ObjectAnimator.ofFloat(mControllerBottom, "alpha",
                if (show) 0f else 1f, if (show) 1f else 0f).setDuration(300)

        addAnimatorListener(show)
    }

    private fun addAnimatorListener(show: Boolean) {
        animatorTop?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                if (show) {
                    mControllerTop.visibility = View.VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (!show) {
                    mControllerTop.visibility = View.GONE
                }
            }
        })
        animatorBottom?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                if (show) {
                    mControllerBottom.visibility = View.VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (!show) {
                    mControllerBottom.visibility = View.GONE
                }
            }
        })

        animatorTop?.start()
        animatorBottom?.start()
    }
}
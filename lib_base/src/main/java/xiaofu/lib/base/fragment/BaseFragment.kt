package xiaofu.lib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.JsonParseException
import xiaofu.lib.base.timer.ITimer
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.json.JSONException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import kotlin.coroutines.CoroutineContext

/**
 *
 * Created by @author xiaofu on 2018/12/7.
 */
abstract class BaseFragment : Fragment(), AnkoLogger, CoroutineScope {
    /**
     * 获取视图ID
     */
    abstract fun getLayoutRes(): Int

    /**
     * 带Tag标签的日志
     * note:真机调试中，info以下级别的日志容易被过滤不显示
     */
    protected val log = AnkoLogger("xiaofu")

    protected lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            initialize(it)
            bindListener()
            requestNetwork()
        } ?: throw RuntimeException("Invalid Activity")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

    abstract fun initialize(activity: FragmentActivity)

    protected open fun bindListener() {}

    protected open fun requestNetwork() {}

    /**
     * 计时任务,如果只关心过程或结果可使用子类接口
     * @param second 秒
     * @param timer 回调
     */
    protected open fun timer(second: Int, timer: ITimer) {
        launch {
            for (i in second downTo 1) {
                timer.onTime(i)
                delay(1000L)
            }
            timer.onTimeEnd()
        }
    }

    protected open fun handleExceptions(t: Throwable?) {
        if (t == null){
            activity?.toast("未知错误")
            return
        }
        val errorMessage = when (t) {
            is SocketException -> "网络异常，请检查网络重试"
            is SocketTimeoutException -> "请求超时,请重新尝试"
            is UnknownHostException -> "您似乎断开了与外网的连接，请检查外网是否畅通后重试"
            is JsonParseException -> "数据解析失败，请联系管理员"
            is JSONException -> "数据解析失败，请联系管理员"
            is ParseException -> "数据解析失败，请联系管理员"
            else -> t.message ?: "未知错误"
        }
        activity?.toast(errorMessage)
    }

}